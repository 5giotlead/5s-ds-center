package com.fgiotlead.ds.center.rsocket.controller;

import com.fgiotlead.ds.center.rsocket.model.service.RSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class ConnectionHandler {

    @Autowired
    RSocketService rSocketService;

    @ConnectMapping
    public Mono<Void> handler(RSocketRequester requester, @AuthenticationPrincipal UserDetails userDetails) {
        rSocketService.registration(requester, userDetails);
        return Mono.empty();
    }
}
