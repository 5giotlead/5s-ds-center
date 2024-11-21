package com.fgiotlead.ds.center.controller;

import com.fgiotlead.ds.center.model.entity.SignageStyleEntity;
import com.fgiotlead.ds.center.model.service.SignageStyleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/signage/style")
@CrossOrigin("*")
public class SignageStyleController {

    @Autowired
    private SignageStyleService signageStyleService;

    @GetMapping("")
    @Operation(summary = "取得播放樣式列表")
    public List<SignageStyleEntity> getStyleList() {
        return signageStyleService.findAll();
    }

    @GetMapping("/{styleId}")
    @Operation(summary = "查詢指定播放樣式", description = "使用 ID 查詢播放樣式")
    public ResponseEntity<SignageStyleEntity> getStyleById(
            @PathVariable(name = "styleId") UUID id
    ) {
        return signageStyleService.findById(id)
                .map(styleEntity -> new ResponseEntity<>(styleEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    @Operation(summary = "新增/修改播放樣式")
    public ResponseEntity<Map<String, String>> saveStyle(
            @Valid @RequestBody SignageStyleEntity style, BindingResult result
    ) {
        Map<String, String> responseEntity = new HashMap<>();
        if (result.hasErrors()) {
            responseEntity.put("message", result.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
        } else {
            Optional<SignageStyleEntity> existStyle = signageStyleService.findByName(style.getName());
            if (existStyle.isEmpty()) {
                if (style.getId() == null) {
                    return signageStyleService.create(style);
                } else {
                    return signageStyleService.update(style);
                }
            } else if (Objects.equals(existStyle.get().getId(), style.getId())) {
                return signageStyleService.update(style);
            } else {
                responseEntity.put("message", "名稱重複");
                return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @DeleteMapping("/{styleId}")
    @Operation(summary = "刪除指定播放樣式", description = "使用 ID 刪除播放樣式")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable(name = "styleId") UUID id) {
        return signageStyleService.delete(id);
    }
}