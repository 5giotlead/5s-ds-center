package com.fgiotlead.ds.center.controller;

import com.fgiotlead.ds.center.model.entity.SignageTemplateEntity;
import com.fgiotlead.ds.center.model.service.SignageTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/signage/template")
@CrossOrigin("*")
public class SignageTemplateController {

    @Autowired
    private SignageTemplateService signageTemplateService;

    @GetMapping("")
    @Operation(summary = "取得版型列表")
    public List<SignageTemplateEntity> getTemplateList() {
        return signageTemplateService.findAll();
    }

    @GetMapping("/{templateId}")
    @Operation(summary = "查詢特定版型", description = "使用 ID 查詢版型")
    public ResponseEntity<SignageTemplateEntity> getTemplateById(
            @PathVariable(name = "templateId") UUID id
    ) {
        return signageTemplateService.findById(id)
                .map(templateEntity -> new ResponseEntity<>(templateEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    @Operation(summary = "新增版型")
    public ResponseEntity<Map<String, String>> saveTemplate(
            @Valid @RequestBody SignageTemplateEntity template, BindingResult result
    ) {
        Map<String, String> responseEntity = new HashMap<>();
        if (result.hasErrors()) {
            responseEntity.put("message", result.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
        } else {
            if (template.getId() == null) {
                return signageTemplateService.create(template);
            } else {
                return signageTemplateService.update(template);
            }
        }
    }

    @DeleteMapping("/{templateId}")
    @Operation(summary = "刪除特定版型", description = "使用 ID 刪除版型")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable(name = "templateId") UUID id) {
        Map<String, String> responseEntity = new HashMap<>();
        Optional<SignageTemplateEntity> template = signageTemplateService.findById(id);
        if (template.isPresent()) {
            signageTemplateService.delete(template.get());
            responseEntity.put("message", "刪除成功");
            return new ResponseEntity<>(responseEntity, HttpStatus.OK);
        } else {
            responseEntity.put("message", "刪除失敗");
            responseEntity.put("state", "Template 不存在");
            return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
        }
    }
}