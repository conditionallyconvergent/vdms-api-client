package com.conditionallyconvergent.channel.deserializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.conditionallyconvergent.channel.VDMSChannelScheduleAdEntry;
import com.conditionallyconvergent.utilities.VDMSDateTimeFormatter;
import lombok.SneakyThrows;

import java.time.Instant;

public class VDMSChannelScheduleAdEntryDeserializer extends VDMSChannelScheduleEntryDeserializer {

    @SneakyThrows
    public static VDMSChannelScheduleAdEntry deserialize(JsonNode node) {
        int offset = (Integer) node.get("offset").numberValue();
        Instant start = VDMSDateTimeFormatter.parse(node.get("start").asText());

        return VDMSChannelScheduleAdEntry.builder()
            .offset(offset)
            .start(start)
            .build();
    }
}
