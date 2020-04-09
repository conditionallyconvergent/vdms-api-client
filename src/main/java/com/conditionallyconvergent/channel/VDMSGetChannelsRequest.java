package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import com.conditionallyconvergent.common.VDMSRequest;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class VDMSGetChannelsRequest extends VDMSRequest {
    @JsonProperty("ids") List<String> ids;

    @Override
    public void validate() throws VDMSInvalidRequestException {
        if (ids == null) {
            throw new VDMSInvalidRequestException("VDMSGetChannelsRequest must specify a list of channel ids.");
        }
    }
}
