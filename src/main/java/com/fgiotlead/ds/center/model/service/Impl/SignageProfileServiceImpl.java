package com.fgiotlead.ds.center.model.service.Impl;

import com.fgiotlead.ds.center.model.entity.SignageProfileEntity;
import com.fgiotlead.ds.center.model.repository.SignageProfileRepository;
import com.fgiotlead.ds.center.model.service.SignageProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class SignageProfileServiceImpl implements SignageProfileService {

    @Autowired
    private SignageProfileRepository signageProfileRepository;

    @Override
    public List<SignageProfileEntity> findAll() {
        return signageProfileRepository.findAll();
    }

    @Override
    public Optional<SignageProfileEntity> findById(UUID id) {
        return signageProfileRepository.findById(id);
    }

    @Override
    public SignageProfileEntity save(SignageProfileEntity signageProfile) {
        return signageProfileRepository.save(signageProfile);
    }

    @Override
    public Map<String, String> delete(SignageProfileEntity signageProfile) {
        Map<String, String> responseEntity = new HashMap<>();
        signageProfileRepository.delete(signageProfile);
        return responseEntity;
    }
}
