package com.fgiotlead.ds.center.event.entity;

import com.fgiotlead.ds.center.model.entity.SignageEdgeEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PublishEvent extends ApplicationEvent {

    private final SignageEdgeEntity signageEdge;

    public PublishEvent(Object source, SignageEdgeEntity signageEdge) {
        super(source);
        this.signageEdge = signageEdge;
    }
}
