package com.fgiotlead.ds.center.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fgiotlead.ds.center.model.entity.SignageEdgeEntity;
import com.fgiotlead.ds.center.model.entity.schedule.SignageScheduleEntity;

import java.util.List;
import java.util.Map;

public class JsonUtils {

    static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static JsonNode stringToJsonNode(String jsonString) throws JsonProcessingException {
        return objectMapper.readTree(jsonString);
    }

    public static JsonNode mapToJsonNode(Map<?, ?> jsonMap) throws JsonProcessingException {
        return objectMapper.valueToTree(jsonMap);
    }

    public static JsonNode objectToJsonNode(Object jsonObject) throws JsonProcessingException {
        return objectMapper.readTree(objectMapper.writeValueAsString(jsonObject));
    }

    public static SignageEdgeEntity jsonNodeToProfile(JsonNode jsonNode) throws JsonProcessingException {
        return objectMapper.treeToValue(jsonNode, SignageEdgeEntity.class);
    }

    public static SignageScheduleEntity jsonNodeToSchedule(JsonNode jsonNode) throws JsonProcessingException {
        return objectMapper.treeToValue(jsonNode, SignageScheduleEntity.class);
    }

    public static String ListToJsonNode(List<?> jsonList) throws JsonProcessingException {
        return objectMapper.writeValueAsString(jsonList);
    }

    public static Map<?, ?> stringToMap(String jsonString) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, Map.class);
    }
}
