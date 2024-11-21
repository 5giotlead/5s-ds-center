package com.fgiotlead.ds.center.model.repository.schedule;

import com.fgiotlead.ds.center.model.entity.SignageProfileEntity;
import com.fgiotlead.ds.center.model.entity.SignageStyleEntity;
import com.fgiotlead.ds.center.model.entity.schedule.RegularScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface RegularScheduleRepository extends JpaRepository<RegularScheduleEntity, UUID> {

    List<RegularScheduleEntity> findAllByStyle(SignageStyleEntity style);


    @Query(value = "SELECT ns FROM RegularScheduleEntity ns " +
            "WHERE ns.profile = ?1 " +
            "AND ns.weekDay = ?2 " +
            "AND NOT (ns.startTime >= ?4 OR ns.endTime <= ?3)"
    )
    List<RegularScheduleEntity> findOverlap(
            SignageProfileEntity profile,
            Integer weekDay,
            LocalTime startTime,
            LocalTime endTime
    );
}
