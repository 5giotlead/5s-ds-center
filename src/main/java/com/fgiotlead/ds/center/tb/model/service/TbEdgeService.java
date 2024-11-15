package com.fgiotlead.ds.center.tb.model.service;

import com.fgiotlead.ds.center.tb.model.entity.TbEdgeEntity;

import java.util.Optional;
import java.util.UUID;

public interface TbEdgeService {
    Optional<TbEdgeEntity> findById(UUID id);

    Optional<TbEdgeEntity> findByRoutingKey(String routingKey);
}
