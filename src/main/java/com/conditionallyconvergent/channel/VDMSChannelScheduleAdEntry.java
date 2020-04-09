package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.core.JsonGenerator;
import com.conditionallyconvergent.utilities.VDMSDateTimeFormatter;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.IOException;
import java.time.Instant;

@Getter
@EqualsAndHashCode
public class VDMSChannelScheduleAdEntry extends VDMSChannelScheduleEntry {
    private VDMSChannelScheduleAdEntry(long offset, Instant start) {
        super("ad", offset, start);
    }

    @Override
    public void serialize(JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", this.getType());
        jsonGenerator.writeNumberField("offset", this.getOffset());
        jsonGenerator.writeStringField("start", VDMSDateTimeFormatter.format(this.getStart()));
        jsonGenerator.writeEndObject();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends VDMSChannelScheduleEntry.Builder<VDMSChannelScheduleAdEntry> {
        @Override
        public VDMSChannelScheduleAdEntry build() {
            return new VDMSChannelScheduleAdEntry(offset, start);
        }
    }
}
