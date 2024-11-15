package com.fgiotlead.ds.center.event.entity;

import com.fgiotlead.ds.center.model.enumEntity.DownlinkStatus;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
public class DownlinkEvent extends ApplicationEvent {

    private final UUID edgeId;
    private final DownlinkStatus status;

    public DownlinkEvent(Object source, UUID edgeId, DownlinkStatus status) {
        super(source);
        this.edgeId = edgeId;
        this.status = status;
    }
}
