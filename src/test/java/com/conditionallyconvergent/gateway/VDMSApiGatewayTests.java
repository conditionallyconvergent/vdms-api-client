package com.conditionallyconvergent.gateway;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.conditionallyconvergent.TestAccount.*;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class VDMSApiGatewayTests {

    private VDMSApiGateway vdmsApiGateway;

    @Before
    public void setup() {
        vdmsApiGateway = new VDMSApiGateway(ROOT_URL, OWNER, SECRET);
    }

    @Test
    public void itListsTheChannelsInTheAccount() throws IOException {
        String listChannelsResponse = vdmsApiGateway.call("/api2/channel/list");

        assertThat(listChannelsResponse).contains("channels");
    }

    @Test
    public void itCanCreateAndDeleteAChannel() throws IOException {
        final String newChannelTitle = "New Channel";
        final String newChannelExternalId = "new-channel-external-id";

        String listChannelsResponseBeforeCreatingAChannel = vdmsApiGateway.call("/api2/channel/list");
        assertThat(listChannelsResponseBeforeCreatingAChannel).contains("channels");
        assertThat(listChannelsResponseBeforeCreatingAChannel).doesNotContain(newChannelTitle);
        assertThat(listChannelsResponseBeforeCreatingAChannel).doesNotContain(newChannelExternalId);

        Map<String, Object> createChannelParams = new HashMap<String, Object>() {{
            put("desc", newChannelTitle);
            put("external_id", newChannelExternalId);
        }};
        String createChannelResponse = vdmsApiGateway.call("/api2/channel/create", createChannelParams);
        assertThat(createChannelResponse).contains(newChannelTitle);
        assertThat(createChannelResponse).contains(newChannelExternalId);

        String listChannelsResponseAfterCreateAChannel = vdmsApiGateway.call("/api2/channel/list");
        assertThat(listChannelsResponseAfterCreateAChannel).contains("channels");
        assertThat(listChannelsResponseAfterCreateAChannel).contains(newChannelTitle);
        assertThat(listChannelsResponseAfterCreateAChannel).contains(newChannelExternalId);

        Map<String, Object> deleteChannelParams = new HashMap<String, Object>() {{
            put("external_ids", singletonList(newChannelExternalId));
        }};
        String deleteChannelResponse = vdmsApiGateway.call("/api2/channel/delete", deleteChannelParams);
        assertThat(deleteChannelResponse).contains("deleted");
        assertThat(deleteChannelResponse).contains(newChannelExternalId);

        String listChannelsResponseAfterDeletingChannel = vdmsApiGateway.call("/api2/channel/list");
        assertThat(listChannelsResponseAfterDeletingChannel).contains("channels");
        assertThat(listChannelsResponseAfterDeletingChannel).doesNotContain(newChannelTitle);
        assertThat(listChannelsResponseAfterDeletingChannel).doesNotContain(newChannelExternalId);
    }

}
