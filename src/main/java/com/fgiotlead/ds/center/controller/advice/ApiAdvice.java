package com.fgiotlead.ds.center.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ApiAdvice {

    @ExceptionHandler(DataAccessException.class)
    public final ResponseEntity<Map<String, String>> handleDataAccessException(DataAccessException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "非預期錯誤");
        log.error(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "非預期錯誤");
        log.error(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Map<String, String>> handleException(Exception e) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "非預期錯誤");
        log.error(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
