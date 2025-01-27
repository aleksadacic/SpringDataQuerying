package com.aleksadacic.springdataquerying.internal.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Tuple;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QueryUtils {
    private QueryUtils() {
    }

    public static <R> List<R> convertToDtoList(Class<R> dtoClass, List<Map<String, Object>> mappedResults, ObjectMapper mapper) {
        List<R> results = new ArrayList<>();
        for (Map<String, Object> mappedResult : mappedResults) {
            results.add(mapper.convertValue(mappedResult, dtoClass));
        }
        return results;
    }

    public static <R> List<Map<String, Object>> mapTuplesToFieldValues(List<Tuple> tuples, Class<R> dtoClass) {
        // Get the list of fields based on the DTO's getters
        List<String> selectedFields = ReflectionUtils.getAttributeNamesFromGetters(dtoClass);

        // Convert each tuple to a map of field names to values
        return tuples.stream()
                .map(tuple -> {
                    Map<String, Object> fieldValueMap = new LinkedHashMap<>();
                    for (String field : selectedFields) {
                        fieldValueMap.put(field, tuple.get(field));
                    }
                    return fieldValueMap;
                })
                .toList();
    }
}
