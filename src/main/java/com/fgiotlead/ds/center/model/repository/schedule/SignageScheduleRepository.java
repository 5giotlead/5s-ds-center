package com.fgiotlead.ds.center.model.repository.schedule;

import com.fgiotlead.ds.center.model.entity.schedule.SignageScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SignageScheduleRepository extends JpaRepository<SignageScheduleEntity, UUID> {
}
