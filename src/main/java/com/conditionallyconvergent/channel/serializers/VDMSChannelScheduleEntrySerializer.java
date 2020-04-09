package com.conditionallyconvergent.channel.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.conditionallyconvergent.channel.VDMSChannelScheduleEntry;

import java.io.IOException;

public class VDMSChannelScheduleEntrySerializer extends JsonSerializer<VDMSChannelScheduleEntry> {
    public void serialize(VDMSChannelScheduleEntry vdmsChannelScheduleEntry, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        vdmsChannelScheduleEntry.serialize(jsonGenerator);
    }
}
