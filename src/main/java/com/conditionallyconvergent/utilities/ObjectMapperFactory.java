package com.conditionallyconvergent.utilities;

import com.conditionallyconvergent.channel.*;
import com.conditionallyconvergent.channel.deserializers.*;
import com.conditionallyconvergent.channel.serializers.VDMSChannelScheduleEntrySerializer;
import com.conditionallyconvergent.channel.serializers.VDMSListAssetsRequestSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ObjectMapperFactory {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final SimpleModule simpleModule = new SimpleModule();

    private static final VDMSChannelScheduleEntrySerializer channelScheduleEntrySerializer = new VDMSChannelScheduleEntrySerializer();
    private static final VDMSListAssetsRequestSerializer listAssetsRequestSerializer = new VDMSListAssetsRequestSerializer();

    private static final VDMSChannelDeserializer channelDeserializer = new VDMSChannelDeserializer();
    private static final VDMSChannelScheduleEntryDeserializer channelScheduleEntryDeserializer = new VDMSChannelScheduleEntryDeserializer();
    private static final VDMSTestPlayerDeserializer testPlayerDeserializer = new VDMSTestPlayerDeserializer();
    private static final VDMSDeletedChannelDeserializer deletedChannelDeserializer = new VDMSDeletedChannelDeserializer();
    private static final VDMSResolvedSlatesDeserializer resolvedSlatesDeserializer = new VDMSResolvedSlatesDeserializer();

    public static ObjectMapper getObjectMapper() {
        simpleModule.addSerializer(VDMSChannelScheduleEntry.class, channelScheduleEntrySerializer);
        simpleModule.addSerializer(VDMSListAssetsRequest.class, listAssetsRequestSerializer);

        simpleModule.addDeserializer(VDMSChannel.class, channelDeserializer);
        simpleModule.addDeserializer(VDMSChannelScheduleEntry.class, channelScheduleEntryDeserializer);
        simpleModule.addDeserializer(VDMSTestPlayer.class, testPlayerDeserializer);
        simpleModule.addDeserializer(VDMSDeletedChannel.class, deletedChannelDeserializer);
        simpleModule.addDeserializer(VDMSResolvedSlates.class, resolvedSlatesDeserializer);

        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}
