package com.fgiotlead.ds.center.event;

import com.fgiotlead.ds.center.event.entity.CheckSettingsEvent;
import com.fgiotlead.ds.center.event.entity.DownlinkEvent;
import com.fgiotlead.ds.center.event.entity.PublishEvent;
import com.fgiotlead.ds.center.model.entity.SignageEdgeEntity;
import com.fgiotlead.ds.center.model.enumEntity.DownlinkStatus;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SignageEventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(Object source, SignageEdgeEntity signageEdge) {
        applicationEventPublisher.publishEvent(new PublishEvent(source, signageEdge));
    }

    public void updateDownlink(Object source, UUID edgeId, DownlinkStatus status) {
        applicationEventPublisher.publishEvent(new DownlinkEvent(source, edgeId, status));
    }

    public void checkSettings(Object source, UUID edgeId) {
        applicationEventPublisher.publishEvent(new CheckSettingsEvent(source, edgeId));
    }
}
