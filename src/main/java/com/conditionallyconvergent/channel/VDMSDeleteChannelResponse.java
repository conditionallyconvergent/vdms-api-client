package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.conditionallyconvergent.common.VDMSResponse;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@JsonDeserialize(builder = VDMSDeleteChannelResponse.VDMSDeleteChannelResponseBuilder.class)
@Value
@Builder
public class VDMSDeleteChannelResponse extends VDMSResponse {
    @JsonProperty("deleted") List<VDMSDeletedChannel> deletedChannels;
    @JsonProperty("error") long error;
    @JsonProperty("msg") String message;

    @JsonPOJOBuilder(withPrefix = "")
    public static class VDMSDeleteChannelResponseBuilder {
    }
}
