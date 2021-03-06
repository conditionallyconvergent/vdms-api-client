package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.conditionallyconvergent.common.VDMSResponse;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@JsonDeserialize(builder = VDMSUpdateChannelScheduleResponse.VDMSUpdateChannelScheduleResponseBuilder.class)
@Value
@Builder
public class VDMSUpdateChannelScheduleResponse extends VDMSResponse {
    @JsonProperty("schedule") List<VDMSChannelScheduleEntry> schedule;
    @JsonProperty("error") long error;
    @JsonProperty("msg") String message;

    @JsonPOJOBuilder(withPrefix = "")
    public static class VDMSUpdateChannelScheduleResponseBuilder {
    }
}
