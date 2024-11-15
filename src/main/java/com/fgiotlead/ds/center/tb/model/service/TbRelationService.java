package com.fgiotlead.ds.center.tb.model.service;

import com.fgiotlead.ds.center.tb.model.entity.TbRelationEntity;

import java.util.List;
import java.util.UUID;

public interface TbRelationService {
    List<TbRelationEntity> findAllById(UUID id);
}
