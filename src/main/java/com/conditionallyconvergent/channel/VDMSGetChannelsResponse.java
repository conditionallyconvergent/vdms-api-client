package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.conditionallyconvergent.common.VDMSResponse;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@JsonDeserialize(builder = VDMSGetChannelsResponse.VDMSGetChannelsResponseBuilder.class)
@Value
@Builder
public class VDMSGetChannelsResponse extends VDMSResponse {
    @JsonProperty("channels") List<VDMSChannel> channels;
    @JsonProperty("error") long error;
    @JsonProperty("msg") String message;

    @JsonPOJOBuilder(withPrefix = "")
    public static class VDMSGetChannelsResponseBuilder {
    }
}
