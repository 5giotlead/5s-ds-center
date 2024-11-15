package com.fgiotlead.ds.center.model.service;

import com.fgiotlead.ds.center.model.entity.SignageProfileEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface SignageProfileService {
    List<SignageProfileEntity> findAll();

    Optional<SignageProfileEntity> findById(UUID id);

    SignageProfileEntity save(SignageProfileEntity signageProfile);

    Map<String, String> delete(SignageProfileEntity signageProfile);

//    Map<String, String> toggle(UUID profileId, boolean enable);
}