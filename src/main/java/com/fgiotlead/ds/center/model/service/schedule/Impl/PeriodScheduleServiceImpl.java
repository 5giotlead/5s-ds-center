package com.fgiotlead.ds.center.model.service.schedule.Impl;//package com.fgiotlead.ds.center.model.service.schedule.Impl;
//
//
//import com.fgiotlead.ds.center.model.entity.SignageGroupEntity;
//import com.fgiotlead.ds.center.model.entity.schedule.PeriodScheduleEntity;
//import com.fgiotlead.ds.center.model.repository.schedule.PeriodScheduleRepository;
//import com.fgiotlead.ds.center.model.service.SignageGroupService;
//import com.fgiotlead.ds.center.model.service.schedule.SignageScheduleService;
//import com.fgiotlead.ds.center.util.ProfileUtils;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.*;
//
//@Service
//@Transactional(rollbackFor = Exception.class)
//@AllArgsConstructor
//public class PeriodScheduleServiceImpl implements SignageScheduleService<PeriodScheduleEntity> {
//
//    private PeriodScheduleRepository periodScheduleRepository;
//    private SignageGroupService signageGroupService;
//
//    @Override
//    public List<PeriodScheduleEntity> findAll() {
//        return periodScheduleRepository.findAll();
//    }
//
//    @Override
//    public Optional<PeriodScheduleEntity> findById(UUID id) {
//        return periodScheduleRepository.findById(id);
//    }
//
//    @Override
//    public ResponseEntity<Map<String, String>> save(PeriodScheduleEntity periodSchedule) {
//        Map<String, String> responseEntity = new HashMap<>();
//        List<PeriodScheduleEntity> schedules = isOverlap(periodSchedule);
//        if (schedules.isEmpty()) {
//            updateDownlinkStatus(periodSchedule);
//            periodScheduleRepository.save(periodSchedule);
//            Optional<SignageGroupEntity> group = signageGroupService.findById(periodSchedule.getGroup().getId());
//            assert group.isPresent();
//            responseEntity.put("message", "新增成功");
//            return new ResponseEntity<>(responseEntity, HttpStatus.OK);
//        } else {
//            responseEntity.put("message", "排程重疊");
//            return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @Override
//    public ResponseEntity<Map<String, String>> update(PeriodScheduleEntity periodSchedule) {
//        Map<String, String> responseEntity = new HashMap<>();
//        Optional<PeriodScheduleEntity> existSchedule = findById(periodSchedule.getId());
//        if (existSchedule.isPresent()) {
//            List<PeriodScheduleEntity> schedules = isOverlap(periodSchedule);
//            if (schedules.size() == 0) {
//                updateDownlinkStatus(periodSchedule);
//                periodScheduleRepository.save(periodSchedule);
//                responseEntity.put("message", "修改成功");
//                return new ResponseEntity<>(responseEntity, HttpStatus.OK);
//            } else {
//                responseEntity.put("message", "排程重疊");
//                return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
//            }
//        } else {
//            responseEntity.put("message", "修改失敗");
//            responseEntity.put("state", "Schedule 不存在");
//            return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @Override
//    public void delete(PeriodScheduleEntity periodSchedule) {
//        updateDownlinkStatus(periodSchedule);
//        periodScheduleRepository.delete(periodSchedule);
//    }
//
//    private List<PeriodScheduleEntity> isOverlap(PeriodScheduleEntity periodSchedule) {
//        return periodScheduleRepository.findOverlap(
//                periodSchedule.getGroup(),
//                periodSchedule.getStartDate(),
//                periodSchedule.getEndDate(),
//                periodSchedule.getStartTime(),
//                periodSchedule.getEndTime()
//        );
//    }
//
//    private void updateDownlinkStatus(PeriodScheduleEntity periodSchedule) {
//        periodSchedule.getGroup().getSignageProfiles().forEach(ProfileUtils::checkDownlinkStatus);
//    }
//}