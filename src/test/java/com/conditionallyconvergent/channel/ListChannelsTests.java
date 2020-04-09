package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import org.junit.Test;

import java.io.IOException;

import static com.conditionallyconvergent.utilities.VDMSResponseAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class ListChannelsTests extends VDMSChannelApiClientTestBase {

    @Test
    public void listChannels_ListsOrSearchesForChannelsInYourAccount() throws IOException, VDMSInvalidRequestException {
        VDMSChannel channel1 = createRandomChannel();
        VDMSChannel channel2 = createRandomChannel();
        VDMSChannel channel3 = createRandomChannel();

        VDMSListChannelsResponse listChannelsResponse = channelApiClient.listChannels();
        assertThat(listChannelsResponse).isSuccessful();

        assertThat(listChannelsResponse.getChannels()).containsExactly(channel1, channel2, channel3);
    }

    @Test
    public void listChannels_CanPaginateTheListOfChannelsInTheAccount() throws IOException, VDMSInvalidRequestException {
        VDMSChannel channel1 = createRandomChannel();
        VDMSChannel channel2 = createRandomChannel();
        VDMSChannel channel3 = createRandomChannel();
        VDMSChannel channel4 = createRandomChannel();

        VDMSListChannelsRequest listChannelsRequest = VDMSListChannelsRequest.builder()
            .skip(1)
            .limit(2)
            .build();

        VDMSListChannelsResponse listChannelsResponse = channelApiClient.listChannels(listChannelsRequest);
        assertThat(listChannelsResponse).isSuccessful();

        assertThat(listChannelsResponse.getChannels()).contains(channel2, channel3);
        assertThat(listChannelsResponse.getChannels()).doesNotContain(channel1, channel4);
    }
}
