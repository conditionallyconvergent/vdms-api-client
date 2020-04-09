package com.conditionallyconvergent.channel.deserializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.conditionallyconvergent.channel.VDMSChannelScheduleSlicerEntry;
import com.conditionallyconvergent.utilities.VDMSDateTimeFormatter;
import lombok.SneakyThrows;

import java.time.Instant;

public class VDMSChannelScheduleSlicerEntryDeserializer extends VDMSChannelScheduleEntryDeserializer {

    @SneakyThrows
    public static VDMSChannelScheduleSlicerEntry deserialize(JsonNode node) {
        int offset = (Integer) node.get("offset").numberValue();
        Instant start = VDMSDateTimeFormatter.parse(node.get("start").asText());
        String slicerId = node.get("slicer_id").asText();

        return VDMSChannelScheduleSlicerEntry.builder()
            .offset(offset)
            .start(start)
            .slicerId(slicerId)
            .build();
    }
}
