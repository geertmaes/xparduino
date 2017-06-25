package com.cegeka.xparduino.configuration;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.queue.ArduinoQueue;
import com.cegeka.xparduino.queue.ArduinoQueueConfiguration;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class ArduinoConfiguration {

    private final Set<Component> components;
    private final ArduinoQueueConfiguration queueConfiguration;

    private ArduinoConfiguration(Set<Component> components, ArduinoQueueConfiguration queueConfiguration) {
        this.components = components;
        this.queueConfiguration = queueConfiguration;
    }

    public Set<Component> getComponents() {
        return components;
    }

    public ArduinoQueue getQueue() {
        return queueConfiguration.getQueue();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Set<Component> components = newHashSet();
        private ArduinoQueueConfiguration queueConfiguration;


        public Builder withComponent(int pin, ComponentType type) {
            this.components.add(new Component(pin, type));
            return this;
        }

        public Builder withQueueConfiguration(ArduinoQueueConfiguration queueConfiguration) {
            this.queueConfiguration = queueConfiguration;
            return this;
        }

        public ArduinoConfiguration build() {
            return new ArduinoConfiguration(components, queueConfiguration);
        }
    }
}
