package com.conditionallyconvergent.utilities;

import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import com.conditionallyconvergent.common.VDMSRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ParamFactory {
    public static Map<String, Object> buildParams(VDMSRequest request) throws VDMSInvalidRequestException {
        request.validate();
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        try {
            return objectMapper.readValue(
                objectMapper.writeValueAsString(request),
                new TypeReference<Map<String, Object>>() {
                }
            );
        } catch (JsonProcessingException e) {
            throw new VDMSInvalidRequestException("Unable to serialize request to JSON.");
        }
    }
}
