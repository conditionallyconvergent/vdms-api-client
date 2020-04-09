package com.conditionallyconvergent.channel.serializers;

import com.conditionallyconvergent.channel.VDMSListAssetsRequest;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;

public class VDMSListAssetsRequestSerializer extends JsonSerializer<VDMSListAssetsRequest> {
    @Override
    public void serialize(VDMSListAssetsRequest vdmsListAssetsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField("id", vdmsListAssetsRequest.getId());
        jsonGenerator.writeStringField("external_id", vdmsListAssetsRequest.getExternalId());

        Instant start = vdmsListAssetsRequest.getStart();
        if (start != null) {
            jsonGenerator.writeNumberField("start", start.getEpochSecond());
        }

        Instant stop = vdmsListAssetsRequest.getStop();
        if (stop != null) {
            jsonGenerator.writeNumberField("end", stop.getEpochSecond());
        }

        jsonGenerator.writeEndObject();
    }
}
