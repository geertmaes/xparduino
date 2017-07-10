package com.cegeka.xparduino.bootstrap.configurator.eventmapper;

import com.cegeka.xparduino.event.Event;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class EventMapperConfig {

    public static EventMapperConfig fromBasePackage(String basePackage) {
        return new EventMapperConfig.Builder()
                .withBasePackage(basePackage)
                .build();
    }

    private final String basePackage;
    private final Set<Class<? extends Event>> events;

    private EventMapperConfig(String basePackage, Set<Class<? extends Event>> events) {
        this.basePackage = basePackage;
        this.events = events;
    }

    String getBasePackage() {
        return basePackage;
    }

    Set<Class<? extends Event>> getEvents() {
        return events;
    }

    public static class Builder {

        private String basePackage;
        private Set<Class<? extends Event>> events = newHashSet();

        public Builder withBasePackage(String basePackage) {
            this.basePackage = basePackage;
            return this;
        }

        public Builder withEvent(Class<? extends Event> eventClass) {
            this.events.add(eventClass);
            return this;
        }

        public EventMapperConfig build() {
            return new EventMapperConfig(basePackage, events);
        }

    }

}
