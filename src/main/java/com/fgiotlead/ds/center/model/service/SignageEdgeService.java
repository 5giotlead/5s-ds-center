package com.fgiotlead.ds.center.model.service;

import com.fgiotlead.ds.center.model.entity.SignageEdgeEntity;
import com.fgiotlead.ds.center.model.enumEntity.DownlinkStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface SignageEdgeService {

    List<SignageEdgeEntity> findAll();

    Optional<SignageEdgeEntity> findById(UUID id);

    ResponseEntity<Map<String, String>> save(SignageEdgeEntity signageEdge);

    void updateStatus(DownlinkStatus downlinkStatus, UUID edgeId);

    Map<String, String> delete(SignageEdgeEntity signageProfile);
}
