package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static com.conditionallyconvergent.utilities.VDMSResponseAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class UpdateChannelTests extends VDMSChannelApiClientTestBase {

    @Test
    public void updateChannel_ModifiesAChannel() throws IOException, VDMSInvalidRequestException {
        VDMSCreateChannelRequest createChannelRequest = VDMSCreateChannelRequest.builder()
            .title("A new channel")
            .externalId("new_channel_external_id")
            .slicerId("new_channel_slicer_id")
            .requireDrm(0)
            .requireStudioDrm(0)
            .slate("new_channel_slate")
            .metadata("{\"keyA\": \"value1\"}")
            .build();

        VDMSCreateChannelResponse createChannelResponse = channelApiClient.createChannel(createChannelRequest);
        assertThat(createChannelResponse).isSuccessful();

        VDMSChannel createdChannel = createChannelResponse.getChannel();

        VDMSUpdateChannelRequest updateChannelRequest = VDMSUpdateChannelRequest.builder()
            .id(createdChannel.getId())
            .title("An updated channel")
            .externalId("updated_channel_external_id")
            .slicerId("updated_channel_slicer_id")
            .requireDrm(1)
            .requireStudioDrm(1)
            .slate("updated_channel_slate")
            .metadata("{\"keyA\": \"value2\", \"keyB\": \"value3\"}")
            .build();

        VDMSUpdateChannelResponse updateChannelResponse = channelApiClient.updateChannel(updateChannelRequest);
        assertThat(updateChannelResponse).isSuccessful();

        VDMSChannel updatedChannel = getChannelWithId(createdChannel.getId());
        assertThat(updateChannelResponse.getChannel()).isEqualTo(updatedChannel);

        assertThat(updatedChannel.getTitle()).isEqualTo("An updated channel");
        assertThat(updatedChannel.getExternalId()).isEqualTo("updated_channel_external_id");
        assertThat(updatedChannel.getSlicerId()).isEqualTo("updated_channel_slicer_id");
        assertThat(updatedChannel.getRequireDRM()).isTrue();
        assertThat(updatedChannel.getRequireStudioDRM()).isTrue();
        assertThat(updatedChannel.getSlate()).isEqualTo("updated_channel_slate");
        assertThat(updatedChannel.getMetadata()).isEqualTo(new HashMap<String, String>() {{ put("keyA", "value2"); put("keyB", "value3"); }});
    }

    @Test(expected = VDMSInvalidRequestException.class)
    public void updateChannel_RequestMustSpecifyAChannelId() throws IOException, VDMSInvalidRequestException {
        VDMSUpdateChannelRequest updateChannelRequest = VDMSUpdateChannelRequest.builder().build();
        channelApiClient.updateChannel(updateChannelRequest);
    }

    @Test(expected = VDMSInvalidRequestException.class)
    public void updateChannel_RequestMetadataMustBeADictionaryInJsonFormat() throws IOException, VDMSInvalidRequestException {
        VDMSUpdateChannelRequest updateChannelRequest = VDMSUpdateChannelRequest.builder()
            .id("an id")
            .metadata("some metadata not in the form of a dictionary in json format")
            .build();

        channelApiClient.updateChannel(updateChannelRequest);
    }
}
