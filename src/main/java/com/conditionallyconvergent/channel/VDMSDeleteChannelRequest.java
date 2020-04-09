package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import com.conditionallyconvergent.common.VDMSRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VDMSDeleteChannelRequest extends VDMSRequest {
    @JsonProperty("id") String id;

    @Override
    public void validate() throws VDMSInvalidRequestException {
        if (id == null) {
            throw new VDMSInvalidRequestException("VDMSDeleteChannelRequest must specify a channel id.");
        }
    }
}
