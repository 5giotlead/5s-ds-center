package com.fgiotlead.ds.center.rsocket.controller;

import com.fgiotlead.ds.center.model.enumEntity.DownlinkStatus;
import com.fgiotlead.ds.center.rsocket.model.service.RSocketService;
import com.fgiotlead.ds.center.tb.model.entity.TbEdgeEntity;
import com.fgiotlead.ds.center.tb.model.service.TbEdgeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
@MessageMapping("signage")
@AllArgsConstructor
public class SignageRemoteController {

    private TbEdgeService tbEdgeService;
    private RSocketService rSocketService;

    @MessageMapping("register")
    public Mono<Void> register(@AuthenticationPrincipal UserDetails userDetails) {
        return Mono.empty();
    }

    @MessageMapping("settings.check")
    public Mono<Void> checkSettings(@AuthenticationPrincipal UserDetails userDetails) {
        Optional<TbEdgeEntity> tbEdgeEntity = tbEdgeService.findByRoutingKey(userDetails.getUsername());
        if (tbEdgeEntity.isPresent()) {
            UUID edgeId = tbEdgeEntity.get().getId();
            rSocketService.checkSettings(edgeId);
        }
        return Mono.empty();
    }

    @MessageMapping("file.{fileId}.download")
    public Flux<ByteBuffer> downloadFile(@DestinationVariable UUID fileId) {
        return rSocketService.fileDownload(fileId);
    }

    @MessageMapping("status")
    public Mono<Void> receiveStatus(@AuthenticationPrincipal UserDetails userDetails, DownlinkStatus downlinkStatus) {
        Optional<TbEdgeEntity> tbEdgeEntity = tbEdgeService.findByRoutingKey(userDetails.getUsername());
        if (tbEdgeEntity.isPresent()) {
            UUID edgeId = tbEdgeEntity.get().getId();
            rSocketService.receiveDownlink(edgeId, downlinkStatus);
        }
        return Mono.empty();
    }

    @MessageMapping("heartbeat")
    public Mono<Void> heartbeat() {
        return Mono.empty();
    }
}