package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.conditionallyconvergent.utilities.VDMSResponseAssert.assertThat;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class GetChannelsTests extends VDMSChannelApiClientTestBase {

    @Test
    public void getChannels_RetrievesInformationAboutSpecificChannelsInYourAccount() throws IOException, VDMSInvalidRequestException {
        VDMSChannel channel1 = createRandomChannel();
        VDMSChannel channel2 = createRandomChannel();
        VDMSChannel channel3 = createRandomChannel();

        VDMSGetChannelsRequest getChannelsRequest = VDMSGetChannelsRequest.builder()
            .ids(asList(channel1.getId(), channel3.getId()))
            .build();

        VDMSGetChannelsResponse getChannelsResponse = channelApiClient.getChannels(getChannelsRequest);
        assertThat(getChannelsResponse).isSuccessful();

        List<VDMSChannel> returnedChannels = getChannelsResponse.getChannels();
        assertThat(returnedChannels).contains(channel1, channel3);
        assertThat(returnedChannels).doesNotContain(channel2);
    }

    @Test(expected = VDMSInvalidRequestException.class)
    public void getChannels_RequestMustSpecifyAListOfIds() throws IOException, VDMSInvalidRequestException {
        VDMSGetChannelsRequest getChannelsRequest = VDMSGetChannelsRequest.builder().build();
        channelApiClient.getChannels(getChannelsRequest);
    }
}
