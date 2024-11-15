package com.fgiotlead.ds.center.model.repository;

import com.fgiotlead.ds.center.model.entity.SignageEdgeEntity;
import com.fgiotlead.ds.center.model.enumEntity.DownlinkStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface SignageEdgeRepository extends JpaRepository<SignageEdgeEntity, UUID> {
    @Modifying
    @Query(value = "UPDATE SignageEdgeEntity " +
            "SET status = ?1 " +
            "WHERE status <> ?1 " +
            "AND id = ?2")
    Integer updateDownlinkStatusById(DownlinkStatus status, UUID id);

    Optional<SignageEdgeEntity> findByIdAndStatusNot(UUID id, DownlinkStatus status);
}
