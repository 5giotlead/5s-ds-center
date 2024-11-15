package com.fgiotlead.ds.center.model.enumEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SignageType {

    DISPLAY(0),
    ePAPER(1);

    private final int code;
}
