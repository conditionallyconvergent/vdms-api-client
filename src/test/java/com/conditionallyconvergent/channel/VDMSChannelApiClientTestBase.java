package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.utilities.RandomStringGenerator;
import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.conditionallyconvergent.TestAccount.*;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class VDMSChannelApiClientTestBase {

    protected VDMSChannelApiClient channelApiClient;

    @Before
    public void setup() {
        channelApiClient = new VDMSChannelApiClient(ROOT_URL, OWNER, SECRET);
    }

    @After
    public void tearDown() throws IOException, VDMSInvalidRequestException {
        deleteAllChannels();
    }

    protected VDMSChannel createRandomChannel() throws IOException, VDMSInvalidRequestException {
        return channelApiClient.createChannel(
            VDMSCreateChannelRequest.builder()
                .title(RandomStringGenerator.randomStringOfLength(10))
                .externalId(RandomStringGenerator.randomStringOfLength(20))
                .build()
        ).getChannel();
    }

    protected VDMSChannel getChannelWithId(String id) throws IOException, VDMSInvalidRequestException {
        VDMSChannel channel = channelApiClient.getChannel(VDMSGetChannelRequest.builder().id(id).build()).getChannel();
        assertThat(channel.getId()).isEqualTo(id);
        return channel;
    }

    private void deleteAllChannels() throws IOException, VDMSInvalidRequestException {
        VDMSListChannelsResponse listChannelsResponse = channelApiClient.listChannels();
        List<String> ids = listChannelsResponse.getChannels().stream()
            .map(VDMSChannel::getId)
            .collect(Collectors.toList());

        VDMSDeleteChannelsRequest deleteChannelsRequest = VDMSDeleteChannelsRequest.builder()
            .ids(ids)
            .build();
        channelApiClient.deleteChannels(deleteChannelsRequest);
    }
}
