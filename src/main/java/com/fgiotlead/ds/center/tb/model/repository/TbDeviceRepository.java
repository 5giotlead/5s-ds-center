package com.fgiotlead.ds.center.tb.model.repository;

import com.fgiotlead.ds.center.tb.model.entity.TbDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TbDeviceRepository extends JpaRepository<TbDeviceEntity, UUID> {

    List<TbDeviceEntity> findAllByType(String type);
}
