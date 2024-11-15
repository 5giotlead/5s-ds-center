package com.fgiotlead.ds.center.tb.model.repository;

import com.fgiotlead.ds.center.tb.model.entity.TbEdgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TbEdgeRepository extends JpaRepository<TbEdgeEntity, UUID> {

    Optional<TbEdgeEntity> findByRoutingKey(String routingKey);
}
