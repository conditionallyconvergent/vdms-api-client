package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.core.JsonGenerator;
import com.conditionallyconvergent.utilities.VDMSDateTimeFormatter;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.IOException;
import java.time.Instant;

@Getter
@EqualsAndHashCode
public class VDMSChannelScheduleSlicerEntry extends VDMSChannelScheduleEntry {
    private final String slicerId;

    private VDMSChannelScheduleSlicerEntry(long offset, Instant start, String slicerId) {
        super("slicer", offset, start);
        this.slicerId = slicerId;
    }

    @Override
    public void serialize(JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", this.getType());
        jsonGenerator.writeNumberField("offset", this.getOffset());
        jsonGenerator.writeStringField("start", VDMSDateTimeFormatter.format(this.getStart()));
        jsonGenerator.writeStringField("slicer_id", this.getSlicerId());
        jsonGenerator.writeEndObject();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends VDMSChannelScheduleEntry.Builder<VDMSChannelScheduleSlicerEntry> {
        private String slicerId;

        public Builder offset(long offset) {
            this.offset = offset;
            return this;
        }

        public Builder start(Instant start) {
            this.start = start;
            return this;
        }

        public Builder slicerId(String slicerId) {
            this.slicerId = slicerId;
            return this;
        }

        public VDMSChannelScheduleSlicerEntry build() {
            return new VDMSChannelScheduleSlicerEntry(offset, start, slicerId);
        }
    }
}
