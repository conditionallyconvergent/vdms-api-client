package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static com.conditionallyconvergent.utilities.VDMSResponseAssert.assertThat;

public class GetChannelScheduleTests extends VDMSChannelApiClientTestBase {

    @Test
    public void getChannelSchedule_ReturnsTheCurrentChannelScheduleForTheSpecifiedChannel() throws IOException, VDMSInvalidRequestException {
        VDMSChannel channel = createRandomChannel();

        VDMSGetChannelScheduleRequest getChannelScheduleRequest = VDMSGetChannelScheduleRequest.builder()
            .id(channel.getId())
            .externalId(channel.getExternalId())
            .build();

        VDMSGetChannelScheduleResponse getChannelScheduleResponse = channelApiClient.getChannelSchedule(getChannelScheduleRequest);
        assertThat(getChannelScheduleResponse).isSuccessful();
        assertThat(getChannelScheduleResponse.getSchedule()).isEmpty();

        VDMSGetChannelScheduleRequest requestWithIdOnly = VDMSGetChannelScheduleRequest.builder()
            .id(channel.getId())
            .build();

        VDMSGetChannelScheduleResponse responseFromIdOnlyRequest = channelApiClient.getChannelSchedule(requestWithIdOnly);
        assertThat(responseFromIdOnlyRequest).isSuccessful();
        assertThat(responseFromIdOnlyRequest.getSchedule()).isEmpty();

        VDMSGetChannelScheduleRequest requestWithExternalIdOnly = VDMSGetChannelScheduleRequest.builder()
            .externalId(channel.getExternalId())
            .build();

        VDMSGetChannelScheduleResponse responseFromExternalIdOnlyRequest = channelApiClient.getChannelSchedule(requestWithExternalIdOnly);
        assertThat(responseFromExternalIdOnlyRequest).isSuccessful();
        assertThat(responseFromExternalIdOnlyRequest.getSchedule()).isEmpty();
    }

    @Test(expected = VDMSInvalidRequestException.class)
    public void getChannelSchedule_RequestMustContainEitherAnIdOrAnExternalId() throws IOException, VDMSInvalidRequestException {
        VDMSGetChannelScheduleRequest getChannelScheduleRequest = VDMSGetChannelScheduleRequest.builder()
            .build();

        channelApiClient.getChannelSchedule(getChannelScheduleRequest);
    }
}
