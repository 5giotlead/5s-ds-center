package com.fgiotlead.ds.center.controller;//package com.fgiotlead.ds.center.controller;
//
//import com.fgiotlead.ds.center.model.entity.schedule.RegularScheduleEntity;
//import com.fgiotlead.ds.center.model.entity.schedule.SignageScheduleEntity;
//import com.fgiotlead.ds.center.model.service.schedule.SignageScheduleService;
//import io.swagger.v3.oas.annotations.Operation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.validation.Valid;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/signage/schedule")
//public class SignageScheduleController {
//
//    @Autowired
//    private SignageScheduleService<RegularScheduleEntity> normalSignageScheduleService;
//    //    private SignageScheduleService<PeriodScheduleEntity> periodSignageScheduleService;
//    @Autowired
//    private SignageScheduleService<SignageScheduleEntity> signageScheduleService;
//
//    @PostMapping("/type:normal")
//    @Operation(summary = "新增/修改常態排程")
//    public ResponseEntity<Map<String, String>> saveNormalSchedule(
//            @Valid @RequestBody RegularScheduleEntity normalSchedule, BindingResult result
//    ) {
//        if (result.hasErrors()) {
//            Map<String, String> response = new HashMap<>();
//            response.put("message", result.getAllErrors().get(0).getDefaultMessage());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        } else {
//            if (normalSchedule.getStartTime().isBefore(normalSchedule.getEndTime())) {
//                if (normalSchedule.getId() == null) {
//                    return normalSignageScheduleService.save(normalSchedule);
//                } else {
//                    return normalSignageScheduleService.update(normalSchedule);
//                }
//            } else {
//                Map<String, String> responseEntity = new HashMap<>();
//                responseEntity.put("message", "開始時間需小於結束時間");
//                return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
//            }
//        }
//    }
//
////    @PostMapping("/type:period")
////    @Operation(summary = "新增/修改特定日期排程")
////    public ResponseEntity<Map<String, String>> savePeriodSchedule(
////            @Valid @RequestBody PeriodScheduleEntity periodSchedule, BindingResult result
////    ) {
////        if (result.hasErrors()) {
////            Map<String, String> response = new HashMap<>();
////            response.put("message", result.getAllErrors().get(0).getDefaultMessage());
////            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
////        } else {
////            if (periodSchedule.getStartTime().isBefore(periodSchedule.getEndTime())) {
////                if (periodSchedule.getId() == null) {
////                    return periodSignageScheduleService.save(periodSchedule);
////                } else {
////                    return periodSignageScheduleService.update(periodSchedule);
////                }
////            } else {
////                Map<String, String> responseEntity = new HashMap<>();
////                responseEntity.put("message", "開始時間需小於結束時間");
////                return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
////            }
////        }
////    }
//
//    @DeleteMapping("/{scheduleId}")
//    @Operation(hidden = true)
//    public ResponseEntity<Map<String, String>> deleteById(@PathVariable(name = "scheduleId") UUID id) {
//        Map<String, String> responseEntity = new HashMap<>();
//        Optional<SignageScheduleEntity> schedule = signageScheduleService.findById(id);
//        if (schedule.isPresent()) {
//            signageScheduleService.delete(schedule.get());
//            responseEntity.put("message", "刪除成功");
//            return new ResponseEntity<>(responseEntity, HttpStatus.OK);
//        } else {
//            responseEntity.put("message", "刪除失敗");
//            responseEntity.put("state", "Schedule 不存在");
//            return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/type:normal/{scheduleId}")
//    @Operation(summary = "刪除星期排程", description = "使用 ID 刪除星期排程")
//    public ResponseEntity<Map<String, String>> deleteNormalById(@PathVariable(name = "scheduleId") UUID id) {
//        Map<String, String> responseEntity = new HashMap<>();
//        Optional<RegularScheduleEntity> normalSchedule = normalSignageScheduleService.findById(id);
//        if (normalSchedule.isPresent()) {
//            normalSignageScheduleService.delete(normalSchedule.get());
//            responseEntity.put("message", "刪除成功");
//            return new ResponseEntity<>(responseEntity, HttpStatus.OK);
//        } else {
//            responseEntity.put("message", "刪除失敗");
//            responseEntity.put("state", "Schedule 不存在");
//            return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
//        }
//    }
//
////    @DeleteMapping("/type:period/{scheduleId}")
////    @Operation(summary = "刪除特定日期排程", description = "使用 ID 刪除特定日期排程")
////    public ResponseEntity<Map<String, String>> deletePeriodById(@PathVariable(name = "scheduleId") UUID id) {
////        Map<String, String> responseEntity = new HashMap<>();
////        Optional<PeriodScheduleEntity> periodSchedule = periodSignageScheduleService.findById(id);
////        if (periodSchedule.isPresent()) {
////            periodSignageScheduleService.delete(periodSchedule.get());
////            responseEntity.put("message", "刪除成功");
////            return new ResponseEntity<>(responseEntity, HttpStatus.OK);
////        } else {
////            responseEntity.put("message", "刪除失敗");
////            responseEntity.put("state", "Schedule 不存在");
////            return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
////        }
////    }
//}