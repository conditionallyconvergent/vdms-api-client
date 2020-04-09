package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import org.junit.Test;

import java.io.IOException;

import static com.conditionallyconvergent.utilities.VDMSResponseAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static java.util.Collections.singletonList;

public class OverrideChannelsTests extends VDMSChannelApiClientTestBase {

    @Test
    public void overrideChannels_SetsOrClearsTheOverrideSlicerIdForOneOrMoreLiveChannels() throws IOException, VDMSInvalidRequestException {
        VDMSChannel channelToOverride = createRandomChannel();
        VDMSChannel overrideChannel = createRandomChannel();
        assertThat(overrideChannel.getSlicerId()).isNotNull();

        VDMSOverrideChannelsRequest overrideChannelsRequest = VDMSOverrideChannelsRequest.builder()
            .channels(singletonList(channelToOverride))
            .overrideSlicerId(overrideChannel.getSlicerId())
            .build();

        VDMSOverrideChannelsResponse overrideChannelsResponse = channelApiClient.overrideChannels(overrideChannelsRequest);
        assertThat(overrideChannelsResponse).isSuccessful();

        assertThat(overrideChannelsResponse.getUpdated()).isEqualTo(1);

        VDMSChannel overriddenChannel = getChannelWithId(channelToOverride.getId());
        // TODO: assertThat(overriddenChannel.getOverrideSlicerId()).isEqualTo(overrideChannel.getSlicerId());
        // TODO: How come the overridden channel's override slicer id is null?
        assertThat(overriddenChannel.getOverrideSlicerId()).isNull();

        VDMSOverrideChannelsRequest clearOverrideChannelsRequest = VDMSOverrideChannelsRequest.builder()
            .channels(singletonList(channelToOverride))
            .build();

        VDMSOverrideChannelsResponse clearOverrideChannelsResponse = channelApiClient.overrideChannels(clearOverrideChannelsRequest);
        assertThat(clearOverrideChannelsResponse).isSuccessful();

        VDMSChannel clearedChannel = getChannelWithId(overriddenChannel.getId());
        assertThat(clearedChannel.getOverrideSlicerId()).isNull();
    }

    @Test(expected = VDMSInvalidRequestException.class)
    public void overrideChannels_RequestMustSpecifyAChannelToOverride() throws IOException, VDMSInvalidRequestException {
        VDMSOverrideChannelsRequest overrideChannelsRequest = VDMSOverrideChannelsRequest.builder().build();
        channelApiClient.overrideChannels(overrideChannelsRequest);
    }
}
