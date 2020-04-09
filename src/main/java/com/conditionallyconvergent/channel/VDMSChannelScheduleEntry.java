package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.core.JsonGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.IOException;
import java.time.Instant;

@Getter
@EqualsAndHashCode
public abstract class VDMSChannelScheduleEntry {
    private final String type;
    private final long offset;
    private final Instant start;

    protected VDMSChannelScheduleEntry(String type, long offset, Instant start) {
        this.type = type;
        this.offset = offset;
        this.start = start;
    }

    public abstract void serialize(JsonGenerator jsonGenerator) throws IOException;

    public abstract static class Builder<T> {
        protected long offset;
        protected Instant start;

        public Builder<T> offset(long offset) {
            this.offset = offset;
            return this;
        }

        public Builder<T> start(Instant start) {
            this.start = start;
            return this;
        }

        public abstract T build();

    }
}
