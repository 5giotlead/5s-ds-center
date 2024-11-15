package com.fgiotlead.ds.center.tb.model.service.impl;

import com.fgiotlead.ds.center.tb.model.entity.TbEdgeEntity;
import com.fgiotlead.ds.center.tb.model.repository.TbEdgeRepository;
import com.fgiotlead.ds.center.tb.model.service.TbEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class TbEdgeServiceImpl implements TbEdgeService {

    @Autowired
    private TbEdgeRepository tbEdgeRepository;

    @Override
    public Optional<TbEdgeEntity> findById(UUID id) {
        return tbEdgeRepository.findById(id);
    }

    @Override
    public Optional<TbEdgeEntity> findByRoutingKey(String routingKey) {
        return tbEdgeRepository.findByRoutingKey(routingKey);
    }
}
