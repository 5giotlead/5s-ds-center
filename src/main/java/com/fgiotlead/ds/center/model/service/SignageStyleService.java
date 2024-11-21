package com.fgiotlead.ds.center.model.service;

import com.fgiotlead.ds.center.model.entity.SignageStyleEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface SignageStyleService {
    List<SignageStyleEntity> findAll();

    Optional<SignageStyleEntity> findById(UUID id);

    Optional<SignageStyleEntity> findByName(String name);

    ResponseEntity<Map<String, String>> create(SignageStyleEntity style);

    ResponseEntity<Map<String, String>> update(SignageStyleEntity style);

    ResponseEntity<Map<String, String>> delete(UUID id);
}
