package com.cegeka.xparduino.queue.stub.emitter;

import com.cegeka.xparduino.event.Event;

import java.util.function.Supplier;

public class EventEmitter {

    private final Supplier<Event> eventSupplier;
    private final EventEmitterConfig emitterConfig;

    private EventEmitter(Supplier<Event> eventSupplier,
                         EventEmitterConfig emitterConfig) {
        this.eventSupplier = eventSupplier;
        this.emitterConfig = emitterConfig;
    }

    public Event nextEvent() {
        return eventSupplier.get();
    }

    public EventEmitterConfig getEmitterConfig() {
        return emitterConfig;
    }

    public static class Builder {
        
        private Supplier<Event> eventSupplier;
        private EventEmitterConfig emitterConfig = EventEmitterConfig.defaultConfig();

        public Builder withEventSupplier(Supplier<Event> eventSupplier) {
            this.eventSupplier = eventSupplier;
            return this;
        }

        public Builder withEmitterConfig(EventEmitterConfig emitterConfig) {
            this.emitterConfig = emitterConfig;
            return this;
        }

        public EventEmitter build() {
            return new EventEmitter(eventSupplier, emitterConfig);
        }
    }
}