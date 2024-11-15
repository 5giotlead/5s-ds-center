package com.fgiotlead.ds.center.model.repository;

import com.fgiotlead.ds.center.model.entity.SignageFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SignageFileRepository extends JpaRepository<SignageFileEntity, UUID> {

    List<SignageFileEntity> findByAccess(String access);

    Optional<SignageFileEntity> findByAccessAndOriginalName(String access, String originalName);
}
