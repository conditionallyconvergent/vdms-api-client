package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.conditionallyconvergent.common.VDMSResponse;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@JsonDeserialize(builder = VDMSListChannelsResponse.VDMSListChannelsResponseBuilder.class)
@Value
@Builder
public class VDMSListChannelsResponse extends VDMSResponse {
    @JsonProperty("channels") List<VDMSChannel> channels;
    @JsonProperty("error") long error;
    @JsonProperty("msg") String message;

    @JsonPOJOBuilder
    public static class VDMSListChannelsResponseBuilder {
    }
}
