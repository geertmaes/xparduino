package com.cegeka.xparduino.bootstrap;

import com.cegeka.xparduino.bootstrap.configurator.component.ComponentConfig;
import com.cegeka.xparduino.bootstrap.configurator.component.ComponentConfigHolder;
import com.cegeka.xparduino.bootstrap.configurator.eventmapper.EventMapperConfig;
import com.cegeka.xparduino.bootstrap.configurator.eventmapper.EventMapperConfigHolder;
import com.cegeka.xparduino.bootstrap.configurator.queue.ArduinoQueueConfig;
import com.cegeka.xparduino.bootstrap.configurator.queue.ArduinoQueueConfigHolder;
import com.cegeka.xparduino.queue.stub.StubQueueConfig;

public class ArduinoConfiguration
        implements ComponentConfigHolder, EventMapperConfigHolder, ArduinoQueueConfigHolder {

    private final ComponentConfig componentConfig;
    private final EventMapperConfig eventMapperConfig;
    private final ArduinoQueueConfig arduinoQueueConfig;

    private ArduinoConfiguration(ComponentConfig componentConfig,
                                 EventMapperConfig eventMapperConfig,
                                 ArduinoQueueConfig arduinoQueueConfig) {
        this.arduinoQueueConfig = arduinoQueueConfig;
        this.componentConfig = componentConfig;
        this.eventMapperConfig = eventMapperConfig;
    }

    public ComponentConfig getComponentConfig() {
        return componentConfig;
    }

    public EventMapperConfig getEventMapperConfig() {
        return eventMapperConfig;
    }

    public ArduinoQueueConfig getArduinoQueueConfig() {
        return arduinoQueueConfig;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private ComponentConfig componentConfig = new ComponentConfig.Builder().build();
        private EventMapperConfig eventMapperConfig = EventMapperConfig.fromBasePackage("com.cegeka.xparduino");
        private ArduinoQueueConfig arduinoQueueConfig = new StubQueueConfig();

        public Builder withComponents(ComponentConfig config) {
            this.componentConfig = config;
            return this;
        }

        public Builder withEventMapper(EventMapperConfig config) {
            this.eventMapperConfig = config;
            return this;
        }

        public Builder withArduinoQueue(ArduinoQueueConfig config) {
            this.arduinoQueueConfig = config;
            return this;
        }

        public ArduinoConfiguration build() {
            return new ArduinoConfiguration(componentConfig, eventMapperConfig, arduinoQueueConfig);
        }

    }

}
