package com.conditionallyconvergent.channel.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.conditionallyconvergent.channel.VDMSChannel;

import java.io.IOException;

public class VDMSChannelDeserializer extends JsonDeserializer<VDMSChannel> {
    @Override
    public VDMSChannel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return VDMSChannel.deserialize(jsonParser.getCodec().readTree(jsonParser));
    }
}
