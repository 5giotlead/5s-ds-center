package com.fgiotlead.ds.center.controller;

import com.fgiotlead.ds.center.model.entity.SignageEdgeEntity;
import com.fgiotlead.ds.center.model.service.SignageEdgeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/signage/edge")
@CrossOrigin("*")
public class SignageEdgeController {
    @Autowired
    private SignageEdgeService signageEdgeService;

    @GetMapping("")
    @Operation(summary = "取得 Edge 設定列表")
    public ResponseEntity<List<SignageEdgeEntity>> findList() {
        return new ResponseEntity<>(signageEdgeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{edgeId}")
    @Operation(summary = "取得指定 Edge 設定")
    public ResponseEntity<SignageEdgeEntity> findById(@PathVariable(name = "edgeId") UUID id) {
        Optional<SignageEdgeEntity> signageEdge = signageEdgeService.findById(id);
        return signageEdge
                .map(edge -> new ResponseEntity<>(edge, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    @Operation(summary = "新增/修改 Edge 設定")
    public ResponseEntity<Map<String, String>> save(
            @Valid @RequestBody SignageEdgeEntity signageEdge, BindingResult result
    ) {
        Map<String, String> responseEntity = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            result.getAllErrors().forEach(
                    error -> errorMessages.add(error.getDefaultMessage())
            );
            responseEntity.put("message", errorMessages.toString());
            return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
        } else {
            return signageEdgeService.save(signageEdge);
        }
    }

//    @PostMapping("/{edgeId}/schedule:save")
//    @Operation(summary = "設定排程")
//    public ResponseEntity<Map<String, String>> saveSchedule(
//            @PathVariable("edgeId") UUID edgeId,
//            @RequestBody Map<SignageType, SignageProfileEntity> profiles
//    ) {
//        Map<String, String> responseEntity = new HashMap<>();
//        if (profiles.isEmpty()) {
//            responseEntity.put("message", "Empty");
//            return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
//        } else {
//            return signageEdgeService.saveSchedule(edgeId, profiles);
//        }
//    }


//    @PostMapping("/{edgeId}/toggleSchedule:{enable}")
//    @Operation(summary = "設定啟用狀態")
//    public ResponseEntity<Map<String, String>> updateEdgeStatus(
//            @PathVariable(name = "edgeId") UUID edgeId,
//            @PathVariable(name = "enable") boolean enable
//    ) {
//        return signageEdgeService.toggleSchedule(edgeId, enable);
//    }

    @DeleteMapping("{edgeId}")
    @Operation(summary = "刪除指定 Edge 設定", description = "使用 ID 刪除 Edge 設定")
    public ResponseEntity<Map<String, String>> deleteSignageProfile(
            @PathVariable(name = "edgeId") UUID id
    ) {
        Map<String, String> responseEntity = new HashMap<>();
        Optional<SignageEdgeEntity> signageProfile = signageEdgeService.findById(id);
        if (signageProfile.isPresent()) {
            signageEdgeService.delete(signageProfile.get());
            responseEntity.put("message", "刪除成功");
            return new ResponseEntity<>(responseEntity, HttpStatus.OK);
        } else {
            responseEntity.put("message", "刪除失敗");
            responseEntity.put("state", "signageProfile 不存在");
        }
        return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
    }
}