package com.conditionallyconvergent.channel.deserializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.conditionallyconvergent.channel.VDMSChannelScheduleEmptyEntry;
import com.conditionallyconvergent.utilities.VDMSDateTimeFormatter;
import lombok.SneakyThrows;

import java.time.Instant;

public class VDMSChannelScheduleEmptyEntryDeserializer extends VDMSChannelScheduleEntryDeserializer {

    @SneakyThrows
    public VDMSChannelScheduleEmptyEntry deserialize(JsonNode node) {
        int offset = (Integer) node.get("offset").numberValue();
        Instant start = VDMSDateTimeFormatter.parse(node.get("start").asText());

        return VDMSChannelScheduleEmptyEntry.builder()
            .offset(offset)
            .start(start)
            .build();
    }
}
