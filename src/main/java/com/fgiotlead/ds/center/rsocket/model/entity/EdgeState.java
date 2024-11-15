package com.fgiotlead.ds.center.rsocket.model.entity;

import com.fgiotlead.ds.center.model.enumEntity.DownlinkStatus;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EdgeState implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Builder.Default
    private DownlinkStatus downlinkState = DownlinkStatus.DEPLOYED;
}
