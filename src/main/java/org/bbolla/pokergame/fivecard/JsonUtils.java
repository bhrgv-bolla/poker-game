package org.bbolla.pokergame.fivecard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

    static ObjectMapper om = new ObjectMapper();

    public static <T> T deserialize(String message, Class<T> aClass) {
        try {
            return om.readValue(message, aClass);
        } catch (IOException e) {
            throw new RuntimeException("Error while de-serializing", e);
        }
    }

    public static String serialize(Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while serializing", e);
        }
    }
}
