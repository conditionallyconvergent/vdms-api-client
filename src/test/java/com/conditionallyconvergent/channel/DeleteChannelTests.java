package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.conditionallyconvergent.utilities.VDMSResponseAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteChannelTests extends VDMSChannelApiClientTestBase {

    @Test
    public void deleteChannel_DeletesAChannelFromYourLibrary() throws IOException, VDMSInvalidRequestException {
        VDMSChannel channel1 = createRandomChannel();
        VDMSChannel channel2 = createRandomChannel();

        VDMSDeleteChannelRequest deleteChannelRequest = VDMSDeleteChannelRequest.builder()
            .id(channel1.getId())
            .build();

        VDMSDeleteChannelResponse deleteChannelResponse = channelApiClient.deleteChannel(deleteChannelRequest);
        assertThat(deleteChannelResponse).isSuccessful();

        assertThat(deleteChannelResponse.getDeletedChannels()).contains(
            VDMSDeletedChannel.builder()
                .id(channel1.getId())
                .externalId(channel1.getExternalId())
                .build()
        );

        List<VDMSChannel> allChannelsInTheAccount = channelApiClient.listChannels().getChannels();
        assertThat(allChannelsInTheAccount).doesNotContain(channel1);
        assertThat(allChannelsInTheAccount).contains(channel2);
    }

    @Test
    public void deleteChannel_DeletingAChannelThatDoesNotExistProducesAnError() throws IOException, VDMSInvalidRequestException {
        VDMSDeleteChannelRequest deleteChannelRequest = VDMSDeleteChannelRequest.builder()
            .id("no-such-channel")
            .build();

        VDMSDeleteChannelResponse deleteChannelResponse = channelApiClient.deleteChannel(deleteChannelRequest);
        assertThat(deleteChannelResponse).isNotSuccessful();
        assertThat(deleteChannelResponse.getMessage()).isEqualTo("Channel(s) not found");
        assertThat(deleteChannelResponse.getDeletedChannels()).isNull();
    }

    @Test(expected = VDMSInvalidRequestException.class)
    public void deleteChannel_RequestMustSpecifyAChannelId() throws IOException, VDMSInvalidRequestException {
        VDMSDeleteChannelRequest deleteChannelRequest = VDMSDeleteChannelRequest.builder().build();
        channelApiClient.deleteChannel(deleteChannelRequest);
    }
}
