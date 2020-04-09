package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.conditionallyconvergent.utilities.VDMSResponseAssert.assertThat;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteChannelsTests extends VDMSChannelApiClientTestBase {

    @Test
    public void deleteChannels_DeletesChannelsFromYourLibrary() throws IOException, VDMSInvalidRequestException {
        VDMSChannel channel1 = createRandomChannel();
        VDMSChannel channel2 = createRandomChannel();
        VDMSChannel channel3 = createRandomChannel();

        VDMSDeleteChannelsRequest deleteChannelsRequest = VDMSDeleteChannelsRequest.builder()
            .ids(asList(channel1.getId(), channel3.getId()))
            .build();

        VDMSDeleteChannelsResponse deleteChannelsResponse = channelApiClient.deleteChannels(deleteChannelsRequest);
        assertThat(deleteChannelsResponse).isSuccessful();

        assertThat(deleteChannelsResponse.getDeletedChannels()).contains(
            VDMSDeletedChannel.builder()
                .id(channel1.getId())
                .externalId(channel1.getExternalId())
                .build(),
            VDMSDeletedChannel.builder()
                .id(channel3.getId())
                .externalId(channel3.getExternalId())
                .build()
        );

        List<VDMSChannel> allChannelsInTheAccount = channelApiClient.listChannels().getChannels();
        assertThat(allChannelsInTheAccount).doesNotContain(channel1, channel3);
        assertThat(allChannelsInTheAccount).contains(channel2);
    }

    @Test
    public void deleteChannels_DeletingAChannelThatDoesNotExistProducesAnError() throws IOException, VDMSInvalidRequestException {
        VDMSDeleteChannelsRequest deleteChannelsRequest = VDMSDeleteChannelsRequest.builder()
            .ids(singletonList("no-such-channel"))
            .build();

        VDMSDeleteChannelsResponse deleteChannelsResponse = channelApiClient.deleteChannels(deleteChannelsRequest);
        assertThat(deleteChannelsResponse).isNotSuccessful();
        assertThat(deleteChannelsResponse.getMessage()).isEqualTo("Channel(s) not found");
        assertThat(deleteChannelsResponse.getDeletedChannels()).isNull();
    }

    @Test(expected = VDMSInvalidRequestException.class)
    public void deleteChannels_RequestMustSpecifyAnId() throws IOException, VDMSInvalidRequestException {
        VDMSDeleteChannelsRequest deleteChannelsRequest = VDMSDeleteChannelsRequest.builder().build();
        channelApiClient.deleteChannels(deleteChannelsRequest);
    }
}
