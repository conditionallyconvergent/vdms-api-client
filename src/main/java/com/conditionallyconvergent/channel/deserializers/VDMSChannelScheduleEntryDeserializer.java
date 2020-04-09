package com.conditionallyconvergent.channel.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.conditionallyconvergent.channel.VDMSChannelScheduleEntry;
import lombok.SneakyThrows;

public class VDMSChannelScheduleEntryDeserializer extends JsonDeserializer<VDMSChannelScheduleEntry> {

    @SneakyThrows
    @Override
    public VDMSChannelScheduleEntry deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        switch (node.get("type").asText()) {
            case "slicer": {
                return VDMSChannelScheduleSlicerEntryDeserializer.deserialize(node);
            }
            case "empty": {
                return new VDMSChannelScheduleEmptyEntryDeserializer().deserialize(node);
            }
            case "asset": {
                return VDMSChannelScheduleAssetEntryDeserializer.deserialize(node);
            }
            case "ad": {
                return VDMSChannelScheduleAdEntryDeserializer.deserialize(node);
            }
        }
        return null;
    }
}
