package com.fgiotlead.ds.center.tb.model.service.impl;

import com.fgiotlead.ds.center.tb.model.entity.TbDeviceEntity;
import com.fgiotlead.ds.center.tb.model.repository.TbDeviceRepository;
import com.fgiotlead.ds.center.tb.model.service.TbDeviceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TbDeviceServiceImpl implements TbDeviceService {

    private TbDeviceRepository tbDeviceRepository;

    @Override
    public List<TbDeviceEntity> findAllByType(String type) {
        return tbDeviceRepository.findAllByType(type);
    }
}
