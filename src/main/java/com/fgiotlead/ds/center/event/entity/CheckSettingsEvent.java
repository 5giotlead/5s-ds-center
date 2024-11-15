package com.fgiotlead.ds.center.event.entity;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
public class CheckSettingsEvent extends ApplicationEvent {

    private final UUID edgeId;

    public CheckSettingsEvent(Object source, UUID edgeId) {
        super(source);
        this.edgeId = edgeId;
    }
}
