package com.fgiotlead.ds.center.controller;

import com.fgiotlead.ds.center.model.entity.SignageFileEntity;
import com.fgiotlead.ds.center.model.entity.SignageStyleEntity;
import com.fgiotlead.ds.center.model.service.SignageFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/signage/file")
@CrossOrigin("*")
public class SignageFileController {
    @Autowired
    private SignageFileService signageFileService;

    @GetMapping("")
    @Operation(summary = "查詢檔案列表")
    public List<SignageFileEntity> getFileList(
            @RequestParam(name = "block", required = false) String block) {
        return signageFileService.findAll(block);
    }

    @GetMapping("/{fileId}")
    @Operation(summary = "查詢指定檔案", description = "使用 ID 查詢檔案")
    public ResponseEntity<SignageFileEntity> getFileById(@PathVariable(name = "fileId") UUID id) {
        return signageFileService.findById(id)
                .map(fileEntity -> new ResponseEntity<>(fileEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "新增檔案")
    public ResponseEntity<Map<String, String>> saveFile(
            @Parameter(schema = @Schema(format = "binary")) @RequestParam(name = "file") MultipartFile multipartFile,
            @RequestParam(name = "access", defaultValue = "all") String access
    ) {
        Map<String, String> responseEntity = new HashMap<>();
        if (!multipartFile.isEmpty()) {
            return signageFileService.save(multipartFile, access);
        } else {
            responseEntity.put("message", "未選擇檔案");
            return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{fileId}/styleSet")
    @Operation(summary = "查詢播放樣式列表", description = "查詢使用到指定檔案的播放樣式列表")
    public Set<SignageStyleEntity> getStyleSetById(@PathVariable(name = "fileId") UUID id) {
        Optional<SignageFileEntity> file = signageFileService.findById(id);
        Set<SignageStyleEntity> styleSet = new HashSet<>();
        file.ifPresent(fileEntity -> fileEntity.getBlocks().forEach(
                signageBlock -> styleSet.add(signageBlock.getStyle())
        ));
        return styleSet;
    }

    @DeleteMapping("/{fileId}")
    @Operation(summary = "刪除指定檔案", description = "使用 ID 刪除檔案")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable(name = "fileId") UUID id) throws IOException {
        return signageFileService.delete(id);
    }
}