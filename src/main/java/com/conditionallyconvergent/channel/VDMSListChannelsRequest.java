package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.conditionallyconvergent.common.VDMSRequest;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VDMSListChannelsRequest extends VDMSRequest {
    @JsonProperty("limit") long limit;
    @JsonProperty("skip") long skip;
}
