package com.fgiotlead.ds.center.model.repository.schedule;//package com.fgiotlead.ds.center.model.repository.schedule;
//
//import com.fgiotlead.ds.center.model.entity.SignageGroupEntity;
//import com.fgiotlead.ds.center.model.entity.schedule.PeriodScheduleEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//import java.util.UUID;
//
//public interface PeriodScheduleRepository extends JpaRepository<PeriodScheduleEntity, UUID> {
//
//    List<PeriodScheduleEntity> findByGroup(SignageGroupEntity group);
//
//    @Query(value = "SELECT ps FROM PeriodScheduleEntity ps " +
//            "WHERE ps.group = ?1 " +
//            "AND NOT (ps.startDate > ?3 OR ps.endDate < ?2) " +
//            "AND NOT (ps.startTime >= ?5 OR ps.endTime <= ?4)"
//    )
//    List<PeriodScheduleEntity> findOverlap(
//            SignageGroupEntity group,
//            LocalDate startDate,
//            LocalDate endDate,
//            LocalTime startTime,
//            LocalTime endTime
//    );
//
//    @Query(value = "SELECT ps FROM PeriodScheduleEntity ps " +
//            "WHERE ps.group = ?1 " +
//            "AND ps.startDate >= ?2 " +
//            "AND ps.endDate <= ?2 " +
//            "AND ps.startTime >= ?3 " +
//            "AND ps.endTime < ?3"
//    )
//    List<PeriodScheduleEntity> findInSchedule(
//            SignageGroupEntity group,
//            LocalDate today,
//            LocalTime now
//    );
//
//}
