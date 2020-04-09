package com.conditionallyconvergent.channel.deserializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.conditionallyconvergent.channel.VDMSChannelScheduleAssetEntry;
import com.conditionallyconvergent.utilities.VDMSDateTimeFormatter;
import lombok.SneakyThrows;

import java.time.Instant;

public class VDMSChannelScheduleAssetEntryDeserializer extends VDMSChannelScheduleEntryDeserializer {

    @SneakyThrows
    public static VDMSChannelScheduleAssetEntry deserialize(JsonNode node) {
        int offset = (Integer) node.get("offset").numberValue();
        Instant start = VDMSDateTimeFormatter.parse(node.get("start").asText());
        String assetId = node.get("asset").asText();
        String externalId = node.get("external_id").asText();

        return VDMSChannelScheduleAssetEntry.builder()
            .offset(offset)
            .start(start)
            .assetId(assetId)
            .externalId(externalId)
            .build();
    }
}
