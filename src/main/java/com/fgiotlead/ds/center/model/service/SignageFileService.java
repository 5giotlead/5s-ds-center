package com.fgiotlead.ds.center.model.service;

import com.fgiotlead.ds.center.model.entity.SignageFileEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface SignageFileService {

    List<SignageFileEntity> findAll(String block);

    Optional<SignageFileEntity> findById(UUID id);

    Optional<SignageFileEntity> findByAccessAndOriginalName(String block, String originalName);

    ResponseEntity<Map<String, String>> save(MultipartFile file, String access);

    ResponseEntity<Map<String, String>> delete(UUID id) throws IOException;
}
