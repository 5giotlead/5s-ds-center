package com.fgiotlead.ds.center.model.service.Impl;


import com.fgiotlead.ds.center.event.entity.CheckSettingsEvent;
import com.fgiotlead.ds.center.event.entity.DownlinkEvent;
import com.fgiotlead.ds.center.event.entity.PublishEvent;
import com.fgiotlead.ds.center.model.entity.SignageEdgeEntity;
import com.fgiotlead.ds.center.model.enumEntity.DownlinkStatus;
import com.fgiotlead.ds.center.model.repository.SignageEdgeRepository;
import com.fgiotlead.ds.center.model.service.SignageEdgeService;
import com.fgiotlead.ds.center.rsocket.model.entity.EdgeState;
import com.fgiotlead.ds.center.rsocket.model.service.RSocketService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class SignageEdgeServiceImpl implements SignageEdgeService {

    private SignageEdgeRepository signageEdgeRepository;
    private RSocketService rSocketService;

    @Override
    public List<SignageEdgeEntity> findAll() {
        return signageEdgeRepository.findAll();
    }

    @Override
    public Optional<SignageEdgeEntity> findById(UUID id) {
        return signageEdgeRepository.findById(id);
    }

    @Override
    public ResponseEntity<Map<String, String>> save(SignageEdgeEntity signageEdge) {
        Map<String, String> responseEntity = new HashMap<>();
//        Optional<TbEdgeEntity> tbEdge = tbEdgeService.findById(signageEdge.getId());
//        if (tbEdge.isPresent()) {
        Optional<SignageEdgeEntity> existEdge = signageEdgeRepository.findById(signageEdge.getId());
        if (existEdge.isEmpty()) {
            signageEdge.setEnable(true);
            responseEntity.put("message", "新增成功");
        } else {
            responseEntity.put("message", "修改成功");
        }
        signageEdge.setStatus(DownlinkStatus.WAITING);
        signageEdge = signageEdgeRepository.save(signageEdge);
        rSocketService.updateDownlinkState(signageEdge.getId(), DownlinkStatus.WAITING);
        this.publish(signageEdge);
        return ResponseEntity.ok(responseEntity);
//        } else {
//            responseEntity.put("message", "資源不存在");
//            return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
//        }
    }

    @Override
    public void updateStatus(DownlinkStatus downlinkStatus, UUID edgeId) {
        int successCount = signageEdgeRepository.updateDownlinkStatusById(downlinkStatus, edgeId);
        if (successCount > 0) {
            rSocketService.updateDownlinkState(edgeId, downlinkStatus);
        }
    }

    @Override
    public Map<String, String> delete(SignageEdgeEntity signageEdge) {
        Map<String, String> responseEntity = new HashMap<>();
        signageEdgeRepository.delete(signageEdge);
        return responseEntity;
    }

//    @Override
//    public ResponseEntity<Map<String, String>> saveSchedule(UUID edgeId, Map<SignageType, SignageProfileEntity> profiles) {
//        Map<String, String> responseEntity = new HashMap<>();
//        Optional<SignageEdgeEntity> signageEdgeEntity = signageEdgeRepository.findById(edgeId);
//        if (signageEdgeEntity.isPresent()) {
//            SignageEdgeEntity signageEdge = signageEdgeEntity.get();
//            profiles.forEach(
//                    (type, profile) -> {
//                        profile.setType(type);
//                        profile.setEdge(signageEdgeEntity.get());
//                    }
//            );
//            signageEdge.setProfiles(profiles);
//            signageEdge.setStatus(SettingsStatus.SYNCING);
//            signageEdge = signageEdgeRepository.save(signageEdge);
//            rSocketService.updateSettingsState(edgeId, SettingsStatus.SYNCING);
//            rSocketService.sendSettings(signageEdge);
//            responseEntity.put("message", "設定成功");
//            return new ResponseEntity<>(responseEntity, HttpStatus.OK);
//        } else {
//            responseEntity.put("message", "資源不存在");
//            return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
//        }
//    }

    @EventListener
    public void checkSettings(CheckSettingsEvent checkSettingsEvent) {
        Optional<SignageEdgeEntity> signageEdgeEntity =
                signageEdgeRepository.findByIdAndStatusNot(checkSettingsEvent.getEdgeId(), DownlinkStatus.DEPLOYED);
        signageEdgeEntity.ifPresent(this::publish);
    }

    @EventListener
    public void publishSettings(PublishEvent publishEvent) {
        SignageEdgeEntity signageEdge = publishEvent.getSignageEdge();
        this.updateStatus(DownlinkStatus.WAITING, signageEdge.getId());
        this.publish(signageEdge);
    }

    @EventListener
    public void receiveStatus(DownlinkEvent downlinkEvent) {
        DownlinkStatus downlinkStatus = downlinkEvent.getStatus();
        EdgeState edgeState = rSocketService.getEdgeState(downlinkEvent.getEdgeId());
        DownlinkStatus downlinkState = edgeState.getDownlinkState();
        if (downlinkState.equals(DownlinkStatus.WAITING)) {
            Optional<SignageEdgeEntity> signageEdgeEntity = signageEdgeRepository.findById(downlinkEvent.getEdgeId());
            signageEdgeEntity.ifPresent(this::publish);
        } else {
            this.updateStatus(downlinkStatus, downlinkEvent.getEdgeId());
        }

    }

    private void publish(SignageEdgeEntity signageEdge) {
        EdgeState edgeState = rSocketService.getEdgeState(signageEdge.getId());
        if (!(edgeState == null ||
                edgeState.getDownlinkState().equals(DownlinkStatus.PENDING))) {
            rSocketService.sendSettings(signageEdge);
            this.updateStatus(DownlinkStatus.PENDING, signageEdge.getId());
        }
    }
}
