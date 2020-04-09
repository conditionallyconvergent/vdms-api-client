package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import org.junit.Test;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static com.conditionallyconvergent.utilities.VDMSResponseAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class ListAssetsTests extends VDMSChannelApiClientTestBase {

    @Test
    public void listAssets_ListsAssetsForALiveChannel() throws IOException, VDMSInvalidRequestException {
        VDMSChannel channel = createRandomChannel();

        VDMSListAssetsRequest listAssetsRequest = VDMSListAssetsRequest.builder()
            .id(channel.getId())
            .start(Instant.now().minus(1, ChronoUnit.DAYS))
            .stop(Instant.now().plus(1, ChronoUnit.DAYS))
            .build();

        VDMSListAssetsResponse listAssetsResponse = channelApiClient.listAssets(listAssetsRequest);
        assertThat(listAssetsResponse).isSuccessful();

        assertThat(listAssetsResponse.getAssetIds()).isEmpty();
    }

    @Test(expected = VDMSInvalidRequestException.class)
    public void listAssets_RequestMustSpecifyEitherAChannelIdOrExternalId() throws IOException, VDMSInvalidRequestException {
        VDMSChannel channel = createRandomChannel();

        VDMSListAssetsRequest requestWithId = VDMSListAssetsRequest.builder()
            .id(channel.getId())
            .build();
        assertThat(channelApiClient.listAssets(requestWithId)).isSuccessful();

        VDMSListAssetsRequest requestWithExternalId = VDMSListAssetsRequest.builder()
            .externalId(channel.getExternalId())
            .build();
        assertThat(channelApiClient.listAssets(requestWithExternalId)).isSuccessful();

        VDMSListAssetsRequest requestWithoutIdOrExternalId = VDMSListAssetsRequest.builder().build();
        channelApiClient.listAssets(requestWithoutIdOrExternalId);
    }

}
