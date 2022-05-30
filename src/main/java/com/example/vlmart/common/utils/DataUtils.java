package com.example.vlmart.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DataUtils {

    public static <T> T getValueOrDefault(T value, T defaultValue) {
        return Optional.ofNullable(value).orElse(defaultValue);
    }

    public static boolean nonEmpty(String text) {
        return !DataUtils.isNullOrEmpty(text);
    }

    public static boolean nonEmpty(Collection collection) {
        return !isNullOrEmpty(collection);
    }

    public static boolean notNullOrEmpty(String text) {
        return !DataUtils.isNullOrEmpty(text);
    }

    public static boolean notNullOrEmpty(Collection collection) {
        return !isNullOrEmpty(collection);
    }

    public static boolean isNullOrEmpty(Collection objects) {
        return objects == null || objects.isEmpty();
    }

    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean notNullOrEmpty(Object object) {
        return !isNullOrEmpty(object);
    }

    public static boolean isNullOrEmpty(Object object) {
        return object == null;
    }

    public static boolean isNullOrZero(Long value) {
        return (value == null || value.equals(0L));
    }

    public static boolean isNullOrZero(String value) {
        return value == null || "0".equals(value);
    }

    public static boolean isNullOrZero(Integer value) {
        return (value == null || value.equals(0));
    }

    public static boolean isNullOrEmpty(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsObj(Object obj1, Object obj2) {
        if (obj1 == null || obj2 == null) return false;
        return obj1.equals(obj2);
    }

    public static Integer parseToInt(Object value, Integer defaultVal) {
        if (value == null) {
            return defaultVal;
        }
        try {
            return Integer.parseInt(String.valueOf(value));
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static Integer parseToInt(Object value) {
        return parseToInt(value, null);
    }

    public static Double parseToDouble(Object value) {
        return parseToDouble(value, null);
    }

    public static Double parseToDouble(Object value, Double defaultVal) {
        try {
            String str = parseToString(value);
            if (DataUtils.isNullOrEmpty(str)) {
                return null;
            }
            return Double.parseDouble(str);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return defaultVal;
        }
    }

    public static String parseToString(Object value, String defaultVal) {
        return Objects.toString(value, defaultVal);
    }

    public static String parseToString(Object value) {
        return parseToString(value, "");
    }

    public static boolean matchByPattern(String value, String regex) {
        if (DataUtils.isNullOrEmpty(regex) || DataUtils.isNullOrEmpty(value)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static String objectToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if (object == null) {
            return null;
        }
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }

    public static <T> T jsonToObject(String json, Class<T> classOutput) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (json == null || json.isEmpty()) {
            return null;
        }
        return mapper.readValue(json, classOutput);
    }

}
