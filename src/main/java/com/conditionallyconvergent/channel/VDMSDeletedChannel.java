package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Value;

import static com.conditionallyconvergent.utilities.JsonNodeDeserializer.stringValue;

@Value
@Builder
public class VDMSDeletedChannel {
    @JsonProperty("external_id") String externalId;
    @JsonProperty("id") String id;

    public static VDMSDeletedChannel deserialize(JsonNode node) {
        return VDMSDeletedChannel.builder()
            .id(stringValue(node.get("id")))
            .externalId(stringValue(node.get("external_id")))
            .build();
    }
}
