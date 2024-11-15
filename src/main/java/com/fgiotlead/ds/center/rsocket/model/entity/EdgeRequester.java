package com.fgiotlead.ds.center.rsocket.model.entity;

import lombok.*;
import org.springframework.messaging.rsocket.RSocketRequester;

import java.io.Serial;
import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EdgeRequester implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private RSocketRequester requester;
    @Builder.Default
    private EdgeState edgeState = EdgeState.builder().build();

}

