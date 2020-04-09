package com.conditionallyconvergent.utilities;

import com.conditionallyconvergent.common.VDMSResponse;
import org.assertj.core.api.AbstractAssert;

public class VDMSResponseAssert extends AbstractAssert<VDMSResponseAssert, VDMSResponse> {
    public VDMSResponseAssert(VDMSResponse vdmsResponse) {
        super(vdmsResponse, VDMSResponseAssert.class);
    }

    public static VDMSResponseAssert assertThat(VDMSResponse actual) {
        return new VDMSResponseAssert(actual);
    }

    public VDMSResponseAssert isSuccessful() {
        boolean isSuccessful = actual.getError() == 0 && actual.getMessage() == null;
        if (!isSuccessful) {
            failWithMessage("Expected response to be successful, i.e. have error == 0 and message == null.");
        }
        return this;
    }

    public VDMSResponseAssert isNotSuccessful() {
        boolean isSuccessful = actual.getError() == 0 && actual.getMessage() == null;
        if (isSuccessful) {
            failWithMessage("Expected response not to be successful, i.e. have non-zero error and non-null message.");
        }
        return this;
    }
}
