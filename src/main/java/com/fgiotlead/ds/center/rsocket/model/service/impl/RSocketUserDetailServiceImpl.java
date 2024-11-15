package com.fgiotlead.ds.center.rsocket.model.service.impl;

import com.fgiotlead.ds.center.tb.model.entity.TbEdgeEntity;
import com.fgiotlead.ds.center.tb.model.repository.TbEdgeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class RSocketUserDetailServiceImpl implements ReactiveUserDetailsService {

    private PasswordEncoder encoder;
    private TbEdgeRepository tbEdgeRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        Optional<TbEdgeEntity> tbEdgeOptional = tbEdgeRepository.findByRoutingKey(username);
        if (tbEdgeOptional.isPresent()) {
            TbEdgeEntity tbEdge = tbEdgeOptional.get();
            return Mono.just(User
                    .withUsername(tbEdge.getRoutingKey())
                    .password(encoder.encode(tbEdge.getSecret()))
                    .roles("EDGE")
                    .build()
            );
        } else {
            return Mono.empty();
        }
    }
}
