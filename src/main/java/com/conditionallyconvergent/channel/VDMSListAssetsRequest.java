package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import com.conditionallyconvergent.common.VDMSRequest;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class VDMSListAssetsRequest extends VDMSRequest {
    String id;
    String externalId;
    Instant start;
    Instant stop;

    @Override
    public void validate() throws VDMSInvalidRequestException {
        if (id == null && externalId == null) {
            throw new VDMSInvalidRequestException("VDMSListAssetsRequest must specify either a channel id or an external id.");
        }
    }
}
