package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import com.conditionallyconvergent.common.VDMSRequest;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VDMSGetChannelRequest extends VDMSRequest {
    @JsonProperty("id") String id;

    @Override
    public void validate() throws VDMSInvalidRequestException {
        if (id == null) {
            throw new VDMSInvalidRequestException("VDMSGetChannelRequest must specify an id.");
        }
    }
}
