package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import com.conditionallyconvergent.common.VDMSRequest;
import com.conditionallyconvergent.utilities.ObjectMapperFactory;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class VDMSUpdateChannelRequest extends VDMSRequest {
    @JsonProperty("id") String id;
    @JsonProperty("title") String title;
    @JsonProperty("external_id") String externalId;
    @JsonProperty("slicer_id") String slicerId;
    @JsonProperty("slicer_owner") String slicerOwner;
    @JsonProperty("require_drm") int requireDrm;
    @JsonProperty("require_studio_drm") int requireStudioDrm;
    @JsonProperty("slate") String slate;
    @JsonProperty("meta") String metadata;

    @Override
    public void validate() throws VDMSInvalidRequestException {
        if(id == null) {
            throw new VDMSInvalidRequestException("VDMSUpdateChannelRequest must specify a channel id.");
        }

        if (metadata == null) {
            return;
        }

        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        try {
            objectMapper.readValue(metadata, new TypeReference<Map<String, String>>() {});
        } catch (JsonProcessingException e) {
            throw new VDMSInvalidRequestException("metadata parameter must be a dictionary in JSON format.");
        }
    }
}
