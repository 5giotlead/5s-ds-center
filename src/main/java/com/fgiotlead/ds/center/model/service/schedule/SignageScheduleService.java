package com.fgiotlead.ds.center.model.service.schedule;

import com.fgiotlead.ds.center.model.entity.schedule.SignageScheduleEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface SignageScheduleService<T extends SignageScheduleEntity> {

    List<T> findAll();

    Optional<T> findById(UUID id);

    ResponseEntity<Map<String, String>> save(T t);

    ResponseEntity<Map<String, String>> update(T t);

    void delete(T t);
}
