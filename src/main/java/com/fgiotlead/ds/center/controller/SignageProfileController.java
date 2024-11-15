package com.fgiotlead.ds.center.controller;

import com.fgiotlead.ds.center.model.entity.SignageProfileEntity;
import com.fgiotlead.ds.center.model.service.SignageProfileService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/signage/profile")
@CrossOrigin("*")
public class SignageProfileController {
    @Autowired
    private SignageProfileService signageProfileService;

    @GetMapping("")
    @Operation(summary = "取得設備設定列表")
    public List<SignageProfileEntity> findList(
    ) {
        return signageProfileService.findAll();
    }

//    @PostMapping("")
//    @Operation(summary = "新增設備設定")
//    public ResponseEntity<Map<String, String>> saveProfile(
//            @Valid @RequestBody SignageProfileEntity signageProfile
//    ) {
//        Map<String, String> response = new HashMap<>();
//        ResponseEntity<Map<String, String>> responseEntity;
//        Optional<SignageProfileEntity> signageProfileEntity = signageProfileService.findById(signageProfile.getId());
//        if (signageProfileEntity.isPresent()) {
//            response.put("message", "設定已存在");
//            responseEntity = new ResponseEntity<>(response, HttpStatus.CONFLICT);
//        } else {
//            response.put("message", "新增成功");
//            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
//        }
//        return responseEntity;
//    }

//    @PostMapping("/{profileId}/toggle:{enable}")
//    @Operation(summary = "修改指定設備啟用狀態")
//    public ResponseEntity<Map<String, String>> updateProfileState(
//            @PathVariable(name = "profileId") UUID profileId,
//            @PathVariable(name = "enable") boolean enable
//    ) {
//        return new ResponseEntity<>(signageProfileService.toggle(profileId, enable), HttpStatus.OK);
//    }

//    @PostMapping("{profileId}/updateInfo")
//    @Operation(summary = "更新指定設備播放紀錄")
//    public ResponseEntity<Map<String, String>> updateProfileInfo(
//            @PathVariable(name = "profileId") UUID assetId,
//            @RequestParam(name = "blockName") String blockName,
//            @RequestParam(name = "fileName") String fileName
//    ) {
//        Map<String, String> responseEntity = new HashMap<>();
//        if (assetId != null && blockName != null && fileName != null) {
//            return signageProfileService.updateInfo(assetId, blockName, fileName);
//        } else {
//            responseEntity.put("message", "輸入資料錯誤");
//        }
//        return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
//    }

    @DeleteMapping("/{profileId}")
    @Operation(summary = "刪除指定設備設定", description = "使用 ID 刪除設備設定")
    public ResponseEntity<Map<String, String>> deleteSignageProfile(
            @PathVariable(name = "profileId") UUID id
    ) {
        Map<String, String> responseEntity = new HashMap<>();
        Optional<SignageProfileEntity> signageProfile = signageProfileService.findById(id);
        if (signageProfile.isPresent()) {
            signageProfileService.delete(signageProfile.get());
            responseEntity.put("message", "刪除成功");
            return new ResponseEntity<>(responseEntity, HttpStatus.OK);
        } else {
            responseEntity.put("message", "刪除失敗");
            responseEntity.put("state", "signageProfile 不存在");
        }
        return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
    }
}