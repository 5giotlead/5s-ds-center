package com.fgiotlead.ds.center.tb.model.service;

import com.fgiotlead.ds.center.tb.model.entity.TbDeviceCredentialsEntity;

import java.util.Optional;
import java.util.UUID;

public interface TbDeviceCredentialsService {
    Optional<TbDeviceCredentialsEntity> findByDeviceId(UUID deviceId);
}
