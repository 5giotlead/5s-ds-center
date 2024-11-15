package com.fgiotlead.ds.center.model.repository;

import com.fgiotlead.ds.center.model.entity.SignageStyleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SignageStyleRepository extends JpaRepository<SignageStyleEntity, UUID> {
    Optional<SignageStyleEntity> findByName(String name);
}
