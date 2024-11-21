package com.fgiotlead.ds.center.model.service.schedule.Impl;


import com.fgiotlead.ds.center.event.SignageEventPublisher;
import com.fgiotlead.ds.center.model.entity.SignageEdgeEntity;
import com.fgiotlead.ds.center.model.entity.SignageProfileEntity;
import com.fgiotlead.ds.center.model.entity.SignageStyleEntity;
import com.fgiotlead.ds.center.model.entity.schedule.RegularScheduleEntity;
import com.fgiotlead.ds.center.model.repository.schedule.RegularScheduleRepository;
import com.fgiotlead.ds.center.model.service.SignageProfileService;
import com.fgiotlead.ds.center.model.service.schedule.SignageScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class RegularScheduleServiceImpl implements SignageScheduleService<RegularScheduleEntity> {

    private RegularScheduleRepository regularScheduleRepository;
    private SignageProfileService signageProfileService;
    private SignageEventPublisher signageEventPublisher;

    @Override
    public List<RegularScheduleEntity> findAll() {
        return regularScheduleRepository.findAll();
    }

    @Override
    public Optional<RegularScheduleEntity> findById(UUID id) {
        return regularScheduleRepository.findById(id);
    }

    @Override
    public List<RegularScheduleEntity> findAllByStyle(SignageStyleEntity style) {
        return regularScheduleRepository.findAllByStyle(style);
    }

    @Override
    public ResponseEntity<Map<String, String>> save(RegularScheduleEntity regularSchedule) {
        Map<String, String> responseEntity = new HashMap<>();
        List<RegularScheduleEntity> schedules = isOverlap(regularSchedule);
        if (schedules.isEmpty()) {
            updateSettingsStatus(regularSchedule);
            regularScheduleRepository.save(regularSchedule);
            Optional<SignageProfileEntity> profile = signageProfileService.findById(regularSchedule.getProfile().getId());
            assert profile.isPresent();
            responseEntity.put("message", "新增成功");
            return new ResponseEntity<>(responseEntity, HttpStatus.OK);
        } else {
            responseEntity.put("message", "新增失敗");
            responseEntity.put("state", "排程重疊");
            return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Map<String, String>> update(RegularScheduleEntity regularSchedule) {
        Map<String, String> responseEntity = new HashMap<>();
        Optional<RegularScheduleEntity> existSchedule = findById(regularSchedule.getId());
        if (existSchedule.isPresent()) {
            List<RegularScheduleEntity> schedules = isOverlap(regularSchedule);
            if (schedules.isEmpty()) {
                updateSettingsStatus(regularSchedule);
                regularScheduleRepository.save(regularSchedule);
                responseEntity.put("message", "修改成功");
                return new ResponseEntity<>(responseEntity, HttpStatus.OK);
            } else {
                responseEntity.put("message", "修改失敗");
                responseEntity.put("state", "排程重疊");
                return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
            }
        } else {
            responseEntity.put("message", "修改失敗");
            responseEntity.put("state", "Schedule 不存在");
            return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void delete(RegularScheduleEntity regularSchedule) {
        regularScheduleRepository.delete(regularSchedule);
        updateSettingsStatus(regularSchedule);
    }

    private List<RegularScheduleEntity> isOverlap(RegularScheduleEntity regularSchedule) {
        return regularScheduleRepository.findOverlap(
                regularSchedule.getProfile(),
                regularSchedule.getWeekDay(),
                regularSchedule.getStartTime(),
                regularSchedule.getEndTime()
        );
    }

    private void updateSettingsStatus(RegularScheduleEntity regularSchedule) {
        SignageEdgeEntity signageEdge = regularSchedule.getProfile().getEdge();
        signageEventPublisher.publish(this, signageEdge);
    }
}
