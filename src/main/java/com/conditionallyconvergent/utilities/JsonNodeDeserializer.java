package com.conditionallyconvergent.utilities;

import com.fasterxml.jackson.databind.JsonNode;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;

public class JsonNodeDeserializer {
    public static String stringValue(JsonNode node) {
        return node == null ? null : node.asText();
    }

    public static URL urlValue(JsonNode node) {
        if (node == null) {
            return null;
        }
        try {
            return new URL(node.asText());
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public static Boolean booleanValue(JsonNode node) {
        return node == null ? null : node.asBoolean();
    }

    public static long longValue(JsonNode node) {
        return node.asLong();
    }

    public static Map<String, String> toMap(JsonNode node) {
        if (node == null) return null;

        Map<String, String> map = new HashMap<>();
        node.fieldNames().forEachRemaining(fieldName -> map.put(fieldName, node.get(fieldName).textValue()));
        return map;
    }

    public static <T> List<T> listValue(JsonNode node, Function<JsonNode, T> deserializer) {
        if (node == null) return null;

        if (node.isArray()) {
            Iterator<JsonNode> elements = node.elements();
            List<T> list = new ArrayList<>();
            elements.forEachRemaining(element -> list.add(deserializer.apply(element)));
            return list;
        }

        return null;
    }
}
