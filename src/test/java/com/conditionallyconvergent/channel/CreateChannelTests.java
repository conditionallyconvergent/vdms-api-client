package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.conditionallyconvergent.utilities.RandomStringGenerator.randomStringOfLength;
import static com.conditionallyconvergent.utilities.VDMSResponseAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateChannelTests extends VDMSChannelApiClientTestBase {

    @Test
    public void createChannel_CreatesANewChannel() throws IOException, VDMSInvalidRequestException {
        final String newChannelTitle = randomStringOfLength(10);

        VDMSListChannelsResponse initialListChannelsResponse = channelApiClient.listChannels();
        assertThat(initialListChannelsResponse).isSuccessful();

        List<String> initialChannelTitles = initialListChannelsResponse.getChannels().stream()
            .map(VDMSChannel::getTitle)
            .collect(Collectors.toList());
        assertThat(initialChannelTitles).doesNotContain(newChannelTitle);

        VDMSCreateChannelRequest createChannelRequest = VDMSCreateChannelRequest.builder()
            .title(newChannelTitle)
            .build();

        VDMSCreateChannelResponse createChannelResponse = channelApiClient.createChannel(createChannelRequest);
        assertThat(createChannelResponse).isSuccessful();

        VDMSChannel createdChannel = createChannelResponse.getChannel();
        assertThat(createdChannel.getTitle()).isEqualTo(newChannelTitle);

        VDMSListChannelsResponse listChannelsAfterCreatingAChannelResponse = channelApiClient.listChannels();
        assertThat(listChannelsAfterCreatingAChannelResponse).isSuccessful();

        List<VDMSChannel> channelsAfterCreatingAChannel = listChannelsAfterCreatingAChannelResponse.getChannels();
        assertThat(channelsAfterCreatingAChannel).contains(createdChannel);
    }

    @Test(expected = VDMSInvalidRequestException.class)
    public void createChannel_RequestMustSpecifyATitle() throws IOException, VDMSInvalidRequestException {
        VDMSCreateChannelRequest createChannelRequest = VDMSCreateChannelRequest.builder().build();
        channelApiClient.createChannel(createChannelRequest);
    }

    @Test(expected = VDMSInvalidRequestException.class)
    public void createChannel_RequestMetadataMustBeADictionaryInJsonFormat() throws IOException, VDMSInvalidRequestException {
        VDMSCreateChannelRequest createChannelRequest = VDMSCreateChannelRequest.builder()
            .title("A new channel")
            .metadata("some metadata not in the form of a dictionary in json format")
            .build();

        channelApiClient.createChannel(createChannelRequest);
    }

    @Test
    public void createChannel_RequestCannotContainBothASlicerIdAndASlicerOwner() throws IOException, VDMSInvalidRequestException {
        VDMSCreateChannelRequest createChannelWithSlicerIdRequest = VDMSCreateChannelRequest.builder()
            .title("A new channel")
            .slicerId("a_slicer_id")
            .build();

        VDMSCreateChannelResponse createChannelWithSlicerIdResponse = channelApiClient.createChannel(createChannelWithSlicerIdRequest);
        assertThat(createChannelWithSlicerIdResponse).isSuccessful();

        VDMSCreateChannelRequest createChannelWithSlicerOwnerRequest = VDMSCreateChannelRequest.builder()
            .title("A new channel")
            .slicerOwner("a_slicer_owner")
            .build();

        VDMSCreateChannelResponse createChannelWithSlicerOwnerResponse = channelApiClient.createChannel(createChannelWithSlicerOwnerRequest);
        assertThat(createChannelWithSlicerOwnerResponse).isSuccessful();

        VDMSCreateChannelRequest createChannelWithBothSlicerIdAndSlicerOwnerRequest = VDMSCreateChannelRequest.builder()
            .title("A new channel")
            .slicerId("a_slicer_id")
            .slicerOwner("a_slicer_owner")
            .build();

        VDMSCreateChannelResponse createChannelWithBothSlicerIdAndSlicerOwnerResponse = channelApiClient.createChannel(createChannelWithBothSlicerIdAndSlicerOwnerRequest);
        assertThat(createChannelWithBothSlicerIdAndSlicerOwnerResponse).isNotSuccessful();
        assertThat(createChannelWithBothSlicerIdAndSlicerOwnerResponse.getMessage()).isEqualTo("Slicer ID can only contain alphanumeric or underscores and a max length of 100");
    }
}
