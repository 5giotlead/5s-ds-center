package com.fgiotlead.ds.center.rsocket.model.service;

import com.fgiotlead.ds.center.model.entity.SignageEdgeEntity;
import com.fgiotlead.ds.center.model.enumEntity.DownlinkStatus;
import com.fgiotlead.ds.center.rsocket.model.entity.EdgeState;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.util.UUID;

public interface RSocketService {

    void registration(RSocketRequester requester, UserDetails userDetails);

    Mono<Void> checkSettings(UUID edgeId);

    Flux<ByteBuffer> fileDownload(UUID fileId);


    void receiveDownlink(UUID edgeId, DownlinkStatus downlinkStatus);

    EdgeState getEdgeState(UUID edgeId);

    void updateDownlinkState(UUID edgeId, DownlinkStatus downlinkStatus);

    void sendSettings(SignageEdgeEntity signageEdge);

}
