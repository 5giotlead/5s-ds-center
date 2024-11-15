package com.fgiotlead.ds.center.model.service.Impl;


import com.fgiotlead.ds.center.event.SignageEventPublisher;
import com.fgiotlead.ds.center.model.entity.SignageBlockEntity;
import com.fgiotlead.ds.center.model.entity.SignageEdgeEntity;
import com.fgiotlead.ds.center.model.repository.SignageBlockRepository;
import com.fgiotlead.ds.center.model.service.SignageBlockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class SignageBlockServiceImpl implements SignageBlockService {

    private SignageBlockRepository signageBlockRepository;
    private SignageEventPublisher signageEventPublisher;

    @Override
    public List<SignageBlockEntity> findAll() {
        return signageBlockRepository.findAll();
    }

    @Override
    public Optional<SignageBlockEntity> findById(UUID id) {
        return signageBlockRepository.findById(id);
    }

    @Override
    public void save(SignageBlockEntity signageBlock) {
        updateSettingsStatus(signageBlock);
        signageBlockRepository.save(signageBlock);
    }

    @Override
    public void delete(SignageBlockEntity signageBlock) {
        signageBlockRepository.delete(signageBlock);
    }

    private void updateSettingsStatus(SignageBlockEntity signageBlock) {
        Set<SignageEdgeEntity> edges = new HashSet<>();
        signageBlock
                .getStyle()
                .getSchedules()
                .forEach(schedule -> edges.add(schedule.getProfile().getEdge()));
        edges.forEach(edge -> signageEventPublisher.publish(this, edge));
    }
}
