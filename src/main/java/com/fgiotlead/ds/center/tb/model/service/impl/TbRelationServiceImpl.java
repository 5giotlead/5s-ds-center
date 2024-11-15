package com.fgiotlead.ds.center.tb.model.service.impl;

import com.fgiotlead.ds.center.tb.model.entity.TbRelationEntity;
import com.fgiotlead.ds.center.tb.model.repository.TbRelationRepository;
import com.fgiotlead.ds.center.tb.model.service.TbRelationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class TbRelationServiceImpl implements TbRelationService {

    private TbRelationRepository tbRelationRepository;

    @Override
    public List<TbRelationEntity> findAllById(UUID id) {
        return tbRelationRepository.findAllById(id);
    }
}
