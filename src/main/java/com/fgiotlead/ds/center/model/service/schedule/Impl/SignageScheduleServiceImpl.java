package com.fgiotlead.ds.center.model.service.schedule.Impl;

import com.fgiotlead.ds.center.model.entity.SignageStyleEntity;
import com.fgiotlead.ds.center.model.entity.schedule.SignageScheduleEntity;
import com.fgiotlead.ds.center.model.repository.schedule.SignageScheduleRepository;
import com.fgiotlead.ds.center.model.service.schedule.SignageScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class SignageScheduleServiceImpl implements SignageScheduleService<SignageScheduleEntity> {
    private SignageScheduleRepository signageScheduleRepository;

    public List<SignageScheduleEntity> findAll() {
        return signageScheduleRepository.findAll();
    }

    @Override
    public Optional<SignageScheduleEntity> findById(UUID id) {
        return signageScheduleRepository.findById(id);

    }

    @Override
    public List<SignageScheduleEntity> findAllByStyle(SignageStyleEntity style) {
        return List.of();
    }

    @Override
    public ResponseEntity<Map<String, String>> save(SignageScheduleEntity schedule) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, String>> update(SignageScheduleEntity schedule) {
        return null;
    }

    @Override
    public void delete(SignageScheduleEntity schedule) {

    }
}
