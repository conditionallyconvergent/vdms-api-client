package com.conditionallyconvergent.channel;

import com.conditionallyconvergent.utilities.VDMSDateTimeFormatter;
import com.fasterxml.jackson.core.JsonGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Getter
@EqualsAndHashCode
public class VDMSChannelScheduleAssetsEntry extends VDMSChannelScheduleEntry {
    private final List<String> assetIds;
    private final List<String> externalIds;

    private VDMSChannelScheduleAssetsEntry(long offset, Instant start, List<String> assetIds, List<String> externalIds) {
        super("asset", offset, start);
        this.assetIds = assetIds;
        this.externalIds = externalIds;
    }

    @Override
    public void serialize(JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", this.getType());
        jsonGenerator.writeNumberField("offset", this.getOffset());
        jsonGenerator.writeStringField("start", VDMSDateTimeFormatter.format(this.getStart()));
        jsonGenerator.writeArrayFieldStart("asset");
        for(String assetId : this.getAssetIds()) {
            jsonGenerator.writeString(assetId);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeArrayFieldStart("external_id");
        for(String externalId : this.getExternalIds()) {
            jsonGenerator.writeString(externalId);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends VDMSChannelScheduleEntry.Builder<VDMSChannelScheduleAssetsEntry> {
        private List<String> assetIds;
        private List<String> externalIds;

        public Builder offset(long offset) {
            this.offset = offset;
            return this;
        }

        public Builder start(Instant start) {
            this.start = start;
            return this;
        }

        public Builder assetIds(List<String> assetIds) {
            this.assetIds = assetIds;
            return this;
        }

        public Builder externalIds(List<String> externalIds) {
            this.externalIds = externalIds;
            return this;
        }

        @Override
        public VDMSChannelScheduleAssetsEntry build() {
            return new VDMSChannelScheduleAssetsEntry(offset, start, assetIds, externalIds);
        }
    }
}
