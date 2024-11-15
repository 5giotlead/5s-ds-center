package com.fgiotlead.ds.center.rsocket.model.service.impl;

import com.fgiotlead.ds.center.event.SignageEventPublisher;
import com.fgiotlead.ds.center.model.entity.SignageEdgeEntity;
import com.fgiotlead.ds.center.model.entity.SignageFileEntity;
import com.fgiotlead.ds.center.model.enumEntity.DownlinkStatus;
import com.fgiotlead.ds.center.model.service.SignageFileService;
import com.fgiotlead.ds.center.rsocket.model.entity.EdgeRequester;
import com.fgiotlead.ds.center.rsocket.model.entity.EdgeState;
import com.fgiotlead.ds.center.rsocket.model.service.RSocketService;
import com.fgiotlead.ds.center.tb.model.entity.TbEdgeEntity;
import com.fgiotlead.ds.center.tb.model.service.TbEdgeService;
import com.fgiotlead.ds.center.util.FileUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@AllArgsConstructor
public class RSocketServiceImpl implements RSocketService {

    private TbEdgeService tbEdgeService;
    private SignageFileService signageFileService;
    private SignageEventPublisher signageEventPublisher;

    private final Map<UUID, EdgeRequester> edgeMap = new ConcurrentHashMap<>();

    @Override
    public void registration(RSocketRequester requester, UserDetails userDetails) {
        Optional<TbEdgeEntity> tbEdgeEntity = tbEdgeService.findByRoutingKey(userDetails.getUsername());
        if (tbEdgeEntity.isPresent()) {
            UUID id = tbEdgeEntity.get().getId();
            Objects.requireNonNull(requester.rsocket())
                    .onClose()
                    .doFirst(() -> this.connect(id, requester))
                    .doFinally(signalType -> this.disconnect(id))
                    .subscribe();
        }
    }

    private void connect(UUID id, RSocketRequester requester) {
        if (this.edgeMap.get(id) != null) {
            this.edgeMap.get(id).getRequester().dispose();
        }
        this.edgeMap.put(id, EdgeRequester.builder().requester(requester).build());
        log.info("<{}> connected.", id);
        this.checkSettings(id);
    }

    private void disconnect(UUID edgeId) {
        this.edgeMap.remove(edgeId);
        log.info("<{}> disconnected.", edgeId);
    }

    @Override
    public Mono<Void> checkSettings(UUID edgeId) {
        // send event to SignageEdgeServiceImpl
        signageEventPublisher.checkSettings(this, edgeId);
        return Mono.empty();
    }

    @Override
    public Flux<ByteBuffer> fileDownload(UUID fileId) {
        Optional<SignageFileEntity> signageFile = signageFileService.findById(fileId);
        return signageFile.map(FileUtils::getFileStream).orElseGet(Flux::empty);
    }

    @Override
    public void receiveDownlink(UUID edgeId, DownlinkStatus downlinkStatus) {
        this.publishEnd(edgeId, downlinkStatus);
    }

    @Override
    public EdgeState getEdgeState(UUID edgeId) {
        EdgeRequester edgeRequester = this.edgeMap.get(edgeId);
        if (edgeRequester != null) {
            return edgeRequester.getEdgeState();
        } else {
            return null;
        }
    }

    @Override
    public void updateDownlinkState(UUID edgeId, DownlinkStatus status) {
        EdgeRequester edgeRequester = this.edgeMap.get(edgeId);
        if (edgeRequester != null && !edgeRequester.getRequester().isDisposed()) {
            EdgeState edgeState = edgeRequester.getEdgeState();
            edgeState.setDownlinkState(status);
        }
    }

    @Override
    public void sendSettings(SignageEdgeEntity signageEdge) {
        UUID edgeId = signageEdge.getId();
        EdgeRequester edgeRequester = this.edgeMap.get(edgeId);
        if (edgeRequester != null &&
                !edgeRequester.getRequester().isDisposed() &&
                !edgeRequester.getEdgeState().getDownlinkState().equals(DownlinkStatus.PENDING)
        ) {
//        if (!(edgeRequester == null ||
//                edgeRequester.getRequester().isDisposed() ||
//                edgeRequester.getEdgeState().getDownlinkState().equals(DownlinkStatus.PENDING)
//        )) {
            edgeRequester.getRequester()
                    .route("edge.publish")
                    .data(signageEdge)
                    .send()
                    .subscribe();
        }
    }

    private void publishEnd(UUID edgeId, DownlinkStatus status) {
        EdgeRequester edgeRequester = this.edgeMap.get(edgeId);
        if (edgeRequester != null) {
            EdgeState edgeState = edgeRequester.getEdgeState();
            if (edgeState.getDownlinkState().equals(DownlinkStatus.WAITING)) {
                signageEventPublisher.checkSettings(this, edgeId);
            } else {
                edgeState.setDownlinkState(status);
                signageEventPublisher.updateDownlink(this, edgeId, status);
            }
        }
    }

}
