package com.conditionallyconvergent.channel.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.conditionallyconvergent.channel.VDMSDeletedChannel;

import java.io.IOException;

public class VDMSDeletedChannelDeserializer extends JsonDeserializer<VDMSDeletedChannel> {
    @Override
    public VDMSDeletedChannel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return VDMSDeletedChannel.deserialize(jsonParser.getCodec().readTree(jsonParser));
    }
}
