package com.fgiotlead.ds.center.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Slf4j
public class Base64Utils {

    public static String encode(List<Map<String, Object>> state) {
        try {
            String stateJson = JsonUtils.ListToJsonNode(state);
            return Base64.getEncoder().encodeToString(stateJson.getBytes());
        } catch (Exception e) {
            log.warn(e.getMessage());
            return "error";
        }
    }

    public static String decode(String base64String) {
        return new String(Base64.getDecoder().decode(base64String));
    }
}
