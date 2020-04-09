package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.conditionallyconvergent.common.VDMSInvalidRequestException;
import com.conditionallyconvergent.gateway.VDMSApiGateway;
import com.conditionallyconvergent.utilities.ObjectMapperFactory;

import java.io.IOException;

import static com.conditionallyconvergent.utilities.ParamFactory.buildParams;

public class VDMSChannelApiClient {
    private final VDMSApiGateway apiGateway;
    private final ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();

    private static final String GET_CHANNEL_ENDPOINT = "/api2/channel/get";
    private static final String UPDATE_CHANNEL_ENDPOINT = "/api2/channel/update";
    private static final String CREATE_CHANNEL_ENDPOINT = "/api2/channel/create";
    private static final String LIST_CHANNEL_ENDPOINT = "/api2/channel/list";
    private static final String DELETE_CHANNEL_ENDPOINT = "/api2/channel/delete";
    private static final String LIST_ASSETS_CHANNEL_ENDPOINT = "/api2/channel/assets";
    private static final String OVERRIDE_CHANNEL_ENDPOINT = "/api2/channel/override";
    private static final String GET_CHANNEL_SCHEDULE_ENDPOINT = "/api2/channel/schedule/get";
    private static final String UPDATE_CHANNEL_SCHEDULE_ENDPOINT = "/api2/channel/schedule/update";

    public VDMSChannelApiClient(String rootUrl, String owner, String secret) {
        this.apiGateway = new VDMSApiGateway(rootUrl, owner, secret);
    }

    public VDMSGetChannelResponse getChannel(VDMSGetChannelRequest request) throws IOException, VDMSInvalidRequestException {
        return objectMapper.readValue(
            apiGateway.call(GET_CHANNEL_ENDPOINT, buildParams(request)),
            new TypeReference<VDMSGetChannelResponse>() {
            }
        );
    }

    public VDMSGetChannelsResponse getChannels(VDMSGetChannelsRequest request) throws IOException, VDMSInvalidRequestException {
        return objectMapper.readValue(
            apiGateway.call(GET_CHANNEL_ENDPOINT, buildParams(request)),
            new TypeReference<VDMSGetChannelsResponse>() {
            }
        );
    }

    public VDMSUpdateChannelResponse updateChannel(VDMSUpdateChannelRequest request) throws IOException, VDMSInvalidRequestException {
        return objectMapper.readValue(
            apiGateway.call(UPDATE_CHANNEL_ENDPOINT, buildParams(request)),
            new TypeReference<VDMSUpdateChannelResponse>() {
            }
        );
    }

    public VDMSCreateChannelResponse createChannel(VDMSCreateChannelRequest request) throws IOException, VDMSInvalidRequestException {
        return objectMapper.readValue(
            apiGateway.call(CREATE_CHANNEL_ENDPOINT, buildParams(request)),
            new TypeReference<VDMSCreateChannelResponse>() {
            }
        );
    }

    public VDMSListChannelsResponse listChannels() throws IOException {
        return objectMapper.readValue(
            apiGateway.call(LIST_CHANNEL_ENDPOINT),
            new TypeReference<VDMSListChannelsResponse>() {
            }
        );
    }

    public VDMSListChannelsResponse listChannels(VDMSListChannelsRequest request) throws IOException, VDMSInvalidRequestException {
        return objectMapper.readValue(
            apiGateway.call(LIST_CHANNEL_ENDPOINT, buildParams(request)),
            new TypeReference<VDMSListChannelsResponse>() {
            }
        );
    }

    public VDMSDeleteChannelResponse deleteChannel(VDMSDeleteChannelRequest request) throws IOException, VDMSInvalidRequestException {
        return objectMapper.readValue(
            apiGateway.call(DELETE_CHANNEL_ENDPOINT, buildParams(request)),
            new TypeReference<VDMSDeleteChannelResponse>() {
            }
        );
    }

    public VDMSDeleteChannelsResponse deleteChannels(VDMSDeleteChannelsRequest request) throws IOException, VDMSInvalidRequestException {
        return objectMapper.readValue(
            apiGateway.call(DELETE_CHANNEL_ENDPOINT, buildParams(request)),
            new TypeReference<VDMSDeleteChannelsResponse>() {
            }
        );
    }

    public VDMSOverrideChannelsResponse overrideChannels(VDMSOverrideChannelsRequest request) throws IOException, VDMSInvalidRequestException {
        return objectMapper.readValue(
            apiGateway.call(OVERRIDE_CHANNEL_ENDPOINT, buildParams(request)),
            new TypeReference<VDMSOverrideChannelsResponse>() {
            }
        );
    }

    public VDMSGetChannelScheduleResponse getChannelSchedule(VDMSGetChannelScheduleRequest request) throws IOException, VDMSInvalidRequestException {
        return objectMapper.readValue(
            apiGateway.call(GET_CHANNEL_SCHEDULE_ENDPOINT, buildParams(request)),
            new TypeReference<VDMSGetChannelScheduleResponse>() {
            }
        );
    }

    public VDMSUpdateChannelScheduleResponse updateChannelSchedule(VDMSUpdateChannelScheduleRequest request) throws VDMSInvalidRequestException, IOException {
        return objectMapper.readValue(
            apiGateway.call(UPDATE_CHANNEL_SCHEDULE_ENDPOINT, buildParams(request)),
            new TypeReference<VDMSUpdateChannelScheduleResponse>() {
            }
        );
    }

    public VDMSListAssetsResponse listAssets(VDMSListAssetsRequest request) throws VDMSInvalidRequestException, IOException {
        return objectMapper.readValue(
            apiGateway.call(LIST_ASSETS_CHANNEL_ENDPOINT, buildParams(request)),
            new TypeReference<VDMSListAssetsResponse>() {
            }
        );
    }
}
