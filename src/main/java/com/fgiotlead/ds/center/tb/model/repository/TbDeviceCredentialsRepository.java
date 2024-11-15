package com.fgiotlead.ds.center.tb.model.repository;

import com.fgiotlead.ds.center.tb.model.entity.TbDeviceCredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TbDeviceCredentialsRepository extends JpaRepository<TbDeviceCredentialsEntity, UUID> {

    Optional<TbDeviceCredentialsEntity> findByDeviceId(UUID deviceId);
}
