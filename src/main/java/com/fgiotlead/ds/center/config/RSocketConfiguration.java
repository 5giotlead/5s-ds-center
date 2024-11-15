package com.fgiotlead.ds.center.config;

import io.rsocket.core.Resume;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.rsocket.server.RSocketServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RSocketConfiguration {

    @Value("${rsocket.connect.keep-alive-duration}")
    private long keepAliveDuration;

    @Bean
    public RSocketServerCustomizer rSocketServerCustomizer() {
        return rSocketServer -> rSocketServer.resume(resumeStrategy());
    }

    private Resume resumeStrategy() {
        return new Resume()
                .sessionDuration(Duration.ofSeconds(keepAliveDuration));
    }

}
