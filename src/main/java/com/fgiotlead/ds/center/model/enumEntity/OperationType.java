package com.fgiotlead.ds.center.model.enumEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationType {

    CREATE(0),
    UPDATE(1),
    DELETE(2);

    private final int code;
}
