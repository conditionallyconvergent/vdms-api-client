package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import org.junit.Test;

import java.io.IOException;

import static com.conditionallyconvergent.utilities.VDMSResponseAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class GetChannelTests extends VDMSChannelApiClientTestBase {

    @Test
    public void getChannel_RetrievesInformationAboutASpecificChannelInYourAccount() throws IOException, VDMSInvalidRequestException {
        VDMSChannel channel = createRandomChannel();

        VDMSGetChannelRequest getChannelRequest = VDMSGetChannelRequest.builder()
            .id(channel.getId())
            .build();

        VDMSGetChannelResponse getChannelResponse = channelApiClient.getChannel(getChannelRequest);
        assertThat(getChannelResponse).isSuccessful();
        assertThat(getChannelResponse.getChannel()).isEqualTo(channel);
    }

    @Test(expected = VDMSInvalidRequestException.class)
    public void getChannel_RequestMustSpecifyAnId() throws IOException, VDMSInvalidRequestException {
        VDMSGetChannelRequest getChannelRequest = VDMSGetChannelRequest.builder().build();
        channelApiClient.getChannel(getChannelRequest);
    }
}
