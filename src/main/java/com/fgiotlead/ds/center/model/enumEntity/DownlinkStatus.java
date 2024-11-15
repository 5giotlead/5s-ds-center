package com.fgiotlead.ds.center.model.enumEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DownlinkStatus {

    WAITING(0),
    PENDING(1),
    DEPLOYED(2),
    ERROR(3);

    private final int code;
}
