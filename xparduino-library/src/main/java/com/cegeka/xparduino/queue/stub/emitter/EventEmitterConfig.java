package com.cegeka.xparduino.queue.stub.emitter;

import java.util.concurrent.TimeUnit;

public class EventEmitterConfig {

    private static int DEFAULT_PERIOD = 100;
    private static int DEFAULT_INITIAL_DELAY = 0;
    private static TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MILLISECONDS;

    public static EventEmitterConfig defaultConfig() {
        return new EventEmitterConfig(DEFAULT_PERIOD, DEFAULT_INITIAL_DELAY, DEFAULT_TIME_UNIT);
    }

    private final int period;
    private final int initialDelay;
    private final TimeUnit timeUnit;

    private EventEmitterConfig(int period, int initialDelay, TimeUnit timeUnit) {
        this.period = period;
        this.initialDelay = initialDelay;
        this.timeUnit = timeUnit;
    }

    public int getPeriod() {
        return period;
    }

    public int getInitialDelay() {
        return initialDelay;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public static class Builder {

        private int period;
        private int initialDelay;
        private TimeUnit timeUnit;

        public Builder withPeriod(int period) {
            this.period = period;
            return this;
        }

        public Builder withInitialDelay(int initialDelay) {
            this.initialDelay = initialDelay;
            return this;
        }

        public Builder withTimeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }

        public EventEmitterConfig build() {
            return new EventEmitterConfig(period, initialDelay, timeUnit);
        }
    }
}