package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import com.conditionallyconvergent.common.VDMSRequest;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class VDMSUpdateChannelScheduleRequest extends VDMSRequest {
    @JsonProperty("id") String id;
    @JsonProperty("external_id") String externalId;
    @JsonProperty("schedule") List<VDMSChannelScheduleEntry> schedule;

    @Override
    public void validate() throws VDMSInvalidRequestException {
        if (id == null && externalId == null) {
            throw new VDMSInvalidRequestException("VDMSUpdateChannelScheduleRequest must specify either an id or an external id.");
        }
    }
}
