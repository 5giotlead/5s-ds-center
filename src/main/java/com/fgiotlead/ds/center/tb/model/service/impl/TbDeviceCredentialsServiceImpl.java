package com.fgiotlead.ds.center.tb.model.service.impl;

import com.fgiotlead.ds.center.tb.model.entity.TbDeviceCredentialsEntity;
import com.fgiotlead.ds.center.tb.model.repository.TbDeviceCredentialsRepository;
import com.fgiotlead.ds.center.tb.model.service.TbDeviceCredentialsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class TbDeviceCredentialsServiceImpl implements TbDeviceCredentialsService {

    private TbDeviceCredentialsRepository tbDeviceCredentialsRepository;

    @Override
    public Optional<TbDeviceCredentialsEntity> findByDeviceId(UUID deviceId) {
        return tbDeviceCredentialsRepository.findByDeviceId(deviceId);
    }
}
