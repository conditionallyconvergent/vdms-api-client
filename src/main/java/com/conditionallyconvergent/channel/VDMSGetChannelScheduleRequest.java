package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import com.conditionallyconvergent.common.VDMSRequest;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VDMSGetChannelScheduleRequest extends VDMSRequest {
    @JsonProperty("id") String id;
    @JsonProperty("external_id") String externalId;

    @Override
    public void validate() throws VDMSInvalidRequestException {
        if(id == null && externalId == null) {
            throw new VDMSInvalidRequestException("VDMSGetChannelScheduleRequest must specify either a channel id or external id.");
        }
    }
}
