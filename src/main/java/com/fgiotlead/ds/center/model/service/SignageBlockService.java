package com.fgiotlead.ds.center.model.service;

import com.fgiotlead.ds.center.model.entity.SignageBlockEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SignageBlockService {
    List<SignageBlockEntity> findAll();

    Optional<SignageBlockEntity> findById(UUID id);

    void save(SignageBlockEntity signageBlock);

    void delete(SignageBlockEntity signageBlock);
}
