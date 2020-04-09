package com.conditionallyconvergent.channel.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.conditionallyconvergent.channel.VDMSResolvedSlates;

import java.io.IOException;

public class VDMSResolvedSlatesDeserializer extends JsonDeserializer<VDMSResolvedSlates> {
    @Override
    public VDMSResolvedSlates deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return VDMSResolvedSlates.deserialize(jsonParser.getCodec().readTree(jsonParser));
    }
}
