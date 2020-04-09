package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.conditionallyconvergent.common.VDMSResponse;
import lombok.Builder;
import lombok.Value;

@JsonDeserialize(builder = VDMSUpdateChannelResponse.VDMSUpdateChannelResponseBuilder.class)
@Value
@Builder
public class VDMSUpdateChannelResponse extends VDMSResponse {
    @JsonProperty("channel") VDMSChannel channel;
    @JsonProperty("error") long error;
    @JsonProperty("msg") String message;

    @JsonPOJOBuilder(withPrefix = "")
    public static class VDMSUpdateChannelResponseBuilder {
    }
}
