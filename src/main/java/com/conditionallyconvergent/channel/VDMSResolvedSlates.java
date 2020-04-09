package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Value;

import static com.conditionallyconvergent.utilities.JsonNodeDeserializer.stringValue;

@Value
@Builder
public class VDMSResolvedSlates {
    @JsonProperty("ad_slate") String adSlate;
    @JsonProperty("blackout_slate") String blackoutSlate;
    @JsonProperty("default_slate") String defaultSlate;
    @JsonProperty("post_slate") String postSlate;
    @JsonProperty("pre_slate") String preSlate;
    @JsonProperty("missing_content_slate") String missingContentSlate;
    @JsonProperty("soon_slate") String soonSlate;

    public static VDMSResolvedSlates deserialize(JsonNode node) {
        return VDMSResolvedSlates.builder()
            .adSlate(stringValue(node.get("ad_slate")))
            .blackoutSlate(stringValue(node.get("blackout_slate")))
            .defaultSlate(stringValue(node.get("default_slate")))
            .postSlate(stringValue(node.get("post_slate")))
            .preSlate(stringValue(node.get("pre_slate")))
            .missingContentSlate(stringValue(node.get("missing_content_slate")))
            .soonSlate(stringValue(node.get("soon_slate")))
            .build();
    }
}
