package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.utilities.VDMSDateTimeFormatter;
import com.fasterxml.jackson.core.JsonGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.IOException;
import java.time.Instant;

@Getter
@EqualsAndHashCode
public class VDMSChannelScheduleAssetEntry extends VDMSChannelScheduleEntry {
    private final String assetId;
    private final String externalId;

    private VDMSChannelScheduleAssetEntry(long offset, Instant start, String assetId, String externalId) {
        super("asset", offset, start);
        this.assetId = assetId;
        this.externalId = externalId;
    }

    @Override
    public void serialize(JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", this.getType());
        jsonGenerator.writeNumberField("offset", this.getOffset());
        jsonGenerator.writeStringField("start", VDMSDateTimeFormatter.format(this.getStart()));
        jsonGenerator.writeStringField("asset", this.getAssetId());
        jsonGenerator.writeStringField("external_id", this.getExternalId());
        jsonGenerator.writeEndObject();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends VDMSChannelScheduleEntry.Builder<VDMSChannelScheduleAssetEntry> {
        private String assetId;
        private String externalId;

        public Builder offset(long offset) {
            this.offset = offset;
            return this;
        }

        public Builder start(Instant start) {
            this.start = start;
            return this;
        }

        public Builder assetId(String assetId) {
            this.assetId = assetId;
            return this;
        }

        public Builder externalId(String externalId) {
            this.externalId = externalId;
            return this;
        }

        @Override
        public VDMSChannelScheduleAssetEntry build() {
            return new VDMSChannelScheduleAssetEntry(offset, start, assetId, externalId);
        }
    }
}
