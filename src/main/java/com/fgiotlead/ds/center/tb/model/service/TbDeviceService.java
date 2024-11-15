package com.fgiotlead.ds.center.tb.model.service;

import com.fgiotlead.ds.center.tb.model.entity.TbDeviceEntity;

import java.util.List;

public interface TbDeviceService {
    List<TbDeviceEntity> findAllByType(String type);
}
