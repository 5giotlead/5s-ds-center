package com.fgiotlead.ds.center.model.service.Impl;


import com.fgiotlead.ds.center.event.SignageEventPublisher;
import com.fgiotlead.ds.center.model.entity.SignageBlockEntity;
import com.fgiotlead.ds.center.model.entity.SignageEdgeEntity;
import com.fgiotlead.ds.center.model.entity.SignageStyleEntity;
import com.fgiotlead.ds.center.model.entity.SignageTemplateEntity;
import com.fgiotlead.ds.center.model.entity.schedule.RegularScheduleEntity;
import com.fgiotlead.ds.center.model.repository.SignageStyleRepository;
import com.fgiotlead.ds.center.model.service.SignageBlockService;
import com.fgiotlead.ds.center.model.service.SignageEdgeService;
import com.fgiotlead.ds.center.model.service.SignageStyleService;
import com.fgiotlead.ds.center.model.service.SignageTemplateService;
import com.fgiotlead.ds.center.model.service.schedule.SignageScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class SignageStyleServiceImpl implements SignageStyleService {

    private SignageStyleRepository signageStyleRepository;
    private SignageEdgeService signageEdgeService;
    private SignageScheduleService<RegularScheduleEntity> regularScheduleService;
    private SignageBlockService signageBlockService;
    private SignageTemplateService signageTemplateService;
    private SignageEventPublisher signageEventPublisher;

    @Override
    public List<SignageStyleEntity> findAll() {
        return signageStyleRepository.findAll();
    }

    @Override
    public Optional<SignageStyleEntity> findById(UUID id) {
        return signageStyleRepository.findById(id);
    }

    @Override
    public Optional<SignageStyleEntity> findByName(String name) {
        return signageStyleRepository.findByName(name);
    }

    @Override
    public ResponseEntity<Map<String, String>> create(SignageStyleEntity style) {
        Map<String, String> responseEntity = new HashMap<>();
        Optional<SignageTemplateEntity> template = signageTemplateService.findById(style.getTemplate().getId());
        style.setEditable(true);
        signageStyleRepository.save(style);
        template.ifPresent(templateEntity -> templateEntity.getBlocksType().forEach(
                (blockName, blockType) ->
                        signageBlockService.save(SignageBlockEntity
                                .builder()
                                .name(blockName)
                                .interval(0)
                                .text(new ArrayList<>())
                                .style(style)
                                .build())
        ));
        responseEntity.put("message", "新增成功");
        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, String>> update(SignageStyleEntity signageStyle) {
        Map<String, String> responseEntity = new HashMap<>();
        if (signageStyleRepository.findById(signageStyle.getId()).isPresent()) {
            SignageStyleEntity style = signageStyleRepository.save(signageStyle);
            this.updateSettingsStatus(regularScheduleService.findAllByStyle(style));
            responseEntity.put("message", "修改成功");
            return new ResponseEntity<>(responseEntity, HttpStatus.OK);
        } else {
            responseEntity.put("message", "修改失敗，樣式不存在");
            return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Map<String, String>> delete(UUID id) {
        Map<String, String> responseEntity = new HashMap<>();
        Optional<SignageStyleEntity> styleOptional = signageStyleRepository.findById(id);
        if (styleOptional.isPresent()) {
            SignageStyleEntity style = styleOptional.get();
            signageStyleRepository.delete(style);
            this.updateSettingsStatus(style.getSchedules());
            responseEntity.put("message", "刪除成功");
            return new ResponseEntity<>(responseEntity, HttpStatus.OK);
        } else {
            responseEntity.put("message", "刪除失敗，樣式不存在");
            return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
        }
    }

    private void updateSettingsStatus(List<RegularScheduleEntity> schedules) {
        Set<SignageEdgeEntity> edges = new HashSet<>();
        schedules.forEach(schedule -> edges.add(schedule.getProfile().getEdge()));
        edges.forEach(edge -> {
            edge.getProfiles().forEach(profile -> profile.getSchedules().removeAll(schedules));
            signageEventPublisher.publish(this, edge);
        });
    }
}
