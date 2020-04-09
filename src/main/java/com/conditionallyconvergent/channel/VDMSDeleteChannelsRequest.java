package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import com.conditionallyconvergent.common.VDMSRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class VDMSDeleteChannelsRequest extends VDMSRequest {
    @JsonProperty("ids") List<String> ids;

    @Override
    public void validate() throws VDMSInvalidRequestException {
        if (ids == null) {
            throw new VDMSInvalidRequestException("VDMSDeleteChannelsRequest must specify a list of channel ids.");
        }
    }
}
