package com.fgiotlead.ds.center.model.service;

import com.fgiotlead.ds.center.model.entity.SignageTemplateEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface SignageTemplateService {

    List<SignageTemplateEntity> findAll();

    Optional<SignageTemplateEntity> findById(UUID id);

    ResponseEntity<Map<String, String>> create(SignageTemplateEntity template);

    ResponseEntity<Map<String, String>> update(SignageTemplateEntity template);

    void delete(SignageTemplateEntity template);
}
