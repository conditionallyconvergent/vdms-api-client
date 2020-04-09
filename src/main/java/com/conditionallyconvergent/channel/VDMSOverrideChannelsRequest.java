package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import com.conditionallyconvergent.common.VDMSRequest;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class VDMSOverrideChannelsRequest extends VDMSRequest {
    @JsonProperty("channels") List<VDMSChannel> channels;
    @JsonProperty("override_slicer_id") String overrideSlicerId;
    @JsonProperty("override_slicer_owner") String overrideSlicerOwner;

    @Override
    public void validate() throws VDMSInvalidRequestException {
        if (channels == null) {
            throw new VDMSInvalidRequestException("VDMSOverrideChannelsRequest must specify a list of channels to override");
        }
    }
}
