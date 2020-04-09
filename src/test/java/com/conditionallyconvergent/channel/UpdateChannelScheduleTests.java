package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import org.junit.Test;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import static com.conditionallyconvergent.utilities.VDMSResponseAssert.assertThat;
import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class UpdateChannelScheduleTests extends VDMSChannelApiClientTestBase {

    @Test
    public void updateChannelSchedule_UpdatesTheChannelScheduleForTheSpecifiedChannel_OverwritingExistingEntries() throws IOException, VDMSInvalidRequestException {
        VDMSChannel channel = createRandomChannel();
        assertThat(getScheduleForChannel(channel)).isEmpty();

        VDMSChannelScheduleSlicerEntry slicerScheduleEntry = VDMSChannelScheduleSlicerEntry.builder()
            .start(Instant.parse("2020-04-05T01:01:01.000Z"))
            .slicerId(channel.getSlicerId())
            .build();
        VDMSChannelScheduleEmptyEntry emptyScheduleEntry = VDMSChannelScheduleEmptyEntry.builder()
            .start(Instant.parse("2020-04-05T02:02:02.000Z"))
            .build();

        VDMSUpdateChannelScheduleRequest updateChannelScheduleRequest = VDMSUpdateChannelScheduleRequest.builder()
            .id(channel.getId())
            .schedule(asList(slicerScheduleEntry, emptyScheduleEntry))
            .build();

        VDMSUpdateChannelScheduleResponse updateChannelScheduleResponse = channelApiClient.updateChannelSchedule(updateChannelScheduleRequest);
        assertThat(updateChannelScheduleResponse).isSuccessful();
        // TODO: Why does the response not contains the entries we just posted?
        // assertThat(updateChannelScheduleResponse.getSchedule()).isNotEmpty();

        assertThat(getScheduleForChannel(channel)).isNotEmpty();
    }

    @Test(expected = VDMSInvalidRequestException.class)
    public void updateChannelSchedule_RequestMustSpecifyEitherAnIdOrAnExternalId() throws IOException, VDMSInvalidRequestException {
        VDMSUpdateChannelScheduleRequest updateChannelScheduleRequest = VDMSUpdateChannelScheduleRequest.builder()
            .build();

        channelApiClient.updateChannelSchedule(updateChannelScheduleRequest);
    }

    private List<VDMSChannelScheduleEntry> getScheduleForChannel(VDMSChannel channel) throws IOException, VDMSInvalidRequestException {
        VDMSGetChannelScheduleRequest request = VDMSGetChannelScheduleRequest.builder()
            .id(channel.getId())
            .build();

        VDMSGetChannelScheduleResponse response = channelApiClient.getChannelSchedule(request);
        assertThat(response).isSuccessful();
        return response.getSchedule();
    }
}
