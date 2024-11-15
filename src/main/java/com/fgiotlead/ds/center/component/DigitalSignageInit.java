package com.fgiotlead.ds.center.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DigitalSignageInit {

    @Autowired
    ObjectMapper objectMapper;

    @PostConstruct
    public void registerJsonModule() {
        objectMapper.registerModule(new JavaTimeModule());
    }
}
