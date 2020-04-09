package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.conditionallyconvergent.common.VDMSResponse;
import lombok.Builder;
import lombok.Value;

@JsonDeserialize(builder = VDMSOverrideChannelsResponse.VDMSOverrideChannelsResponseBuilder.class)
@Value
@Builder
public class VDMSOverrideChannelsResponse extends VDMSResponse {
    @JsonProperty("updated") long updated;
    @JsonProperty("error") long error;
    @JsonProperty("msg") String message;

    @JsonPOJOBuilder(withPrefix = "")
    public static class VDMSOverrideChannelsResponseBuilder {
    }
}
