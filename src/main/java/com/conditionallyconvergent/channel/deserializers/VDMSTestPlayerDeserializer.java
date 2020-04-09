package com.conditionallyconvergent.channel.deserializers;

import com.conditionallyconvergent.channel.VDMSTestPlayer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class VDMSTestPlayerDeserializer extends JsonDeserializer<VDMSTestPlayer> {
    @Override
    public VDMSTestPlayer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return VDMSTestPlayer.deserialize(jsonParser.getCodec().readTree(jsonParser));
    }
}
