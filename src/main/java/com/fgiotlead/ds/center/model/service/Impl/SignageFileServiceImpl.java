package com.fgiotlead.ds.center.model.service.Impl;

import com.fgiotlead.ds.center.event.SignageEventPublisher;
import com.fgiotlead.ds.center.model.entity.SignageEdgeEntity;
import com.fgiotlead.ds.center.model.entity.SignageFileEntity;
import com.fgiotlead.ds.center.model.repository.SignageFileRepository;
import com.fgiotlead.ds.center.model.service.SignageFileService;
import com.fgiotlead.ds.center.util.FileUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class SignageFileServiceImpl implements SignageFileService {

    private SignageFileRepository signageFileRepository;
    //    private SignageBlockService signageBlockService;
    private SignageEventPublisher signageEventPublisher;

    @Override
    public List<SignageFileEntity> findAll(String access) {
        if (access == null) {
            return signageFileRepository.findAll();
        } else {
            return signageFileRepository.findByAccess(access);
        }
    }

    @Override
    public Optional<SignageFileEntity> findById(UUID id) {
        return signageFileRepository.findById(id);
    }

    @Override
    public Optional<SignageFileEntity> findByAccessAndOriginalName(String block, String originalName) {
        return signageFileRepository.findByAccessAndOriginalName(block, originalName);
    }

    @Override
    public ResponseEntity<Map<String, String>> save(MultipartFile file, String access) {
        boolean isSuccess = false;
        Map<String, String> responseEntity = new HashMap<>();
        String originalName = file.getOriginalFilename();
        String fileName = originalName != null ? originalName.substring(0, originalName.lastIndexOf('.')) : null;
        if (fileName != null && signageFileRepository.findByAccessAndOriginalName(access, fileName).isEmpty()) {
            try {
                SignageFileEntity signageFile = FileUtils.upload(file, access);
                if (signageFile != null) {
                    signageFileRepository.save(signageFile);
                    responseEntity.put("message", "新增成功");
                    isSuccess = true;
                } else {
                    responseEntity.put("message", "新增失敗");
                }
            } catch (NoSuchAlgorithmException e) {
                log.warn(e.getMessage());
                responseEntity.put("message", "非預期錯誤");
            }
        } else {
            responseEntity.put("message", "檔案已存在");
        }
        return new ResponseEntity<>(responseEntity, isSuccess ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Map<String, String>> delete(UUID id) throws IOException {
        Map<String, String> responseEntity = new HashMap<>();
        Optional<SignageFileEntity> file = signageFileRepository.findById(id);
        if (file.isPresent()) {
            try {
//                if (!file.get().getBlocks().isEmpty()) {
//                    responseEntity.put("message", "刪除失敗，此檔案已在現有樣式中使用。");
//                    return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
//                } else {
                file.get().getBlocks().forEach(
                        block -> block.getFiles().remove(file.get())
                );
                signageFileRepository.delete(file.get());
                FileUtils.delete(file.get());
                this.updateSettingsStatus(file.get());
                responseEntity.put("message", "刪除成功");
                return new ResponseEntity<>(responseEntity, HttpStatus.OK);
//                }
            } catch (DataAccessException | IOException e) {
                if (e instanceof DataAccessException dae) {
                    FileUtils.restore(file.get());
                    log.warn(dae.getMessage());
                }
                responseEntity.put("message", "檔案刪除失敗");
                return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
            }
        } else {
            responseEntity.put("message", "找不到檔案");
            return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
        }
    }

    private void updateSettingsStatus(SignageFileEntity signageFile) {
        Set<SignageEdgeEntity> edges = new HashSet<>();
        signageFile.getBlocks()
                .forEach(block -> block.getStyle().getSchedules()
                        .forEach(schedule -> edges.add(schedule.getProfile().getEdge()))
                );
        edges.forEach(edge -> signageEventPublisher.publish(this, edge));
    }
}
