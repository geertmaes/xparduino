package com.cegeka.xparduino.bootstrap;

import com.cegeka.xparduino.bootstrap.configurator.component.ComponentConfig;
import com.cegeka.xparduino.bootstrap.configurator.component.ComponentConfigHolder;
import com.cegeka.xparduino.bootstrap.configurator.objectmapper.ObjectMapperConfig;
import com.cegeka.xparduino.bootstrap.configurator.objectmapper.ObjectMapperConfigHolder;
import com.cegeka.xparduino.bootstrap.configurator.queue.ArduinoQueueConfig;
import com.cegeka.xparduino.bootstrap.configurator.queue.ArduinoQueueConfigHolder;
import com.cegeka.xparduino.queue.stub.StubQueueConfig;

public class ArduinoConfiguration
        implements ComponentConfigHolder, ObjectMapperConfigHolder, ArduinoQueueConfigHolder {

    private final ComponentConfig componentConfig;
    private final ObjectMapperConfig objectMapperConfig;
    private final ArduinoQueueConfig arduinoQueueConfig;

    private ArduinoConfiguration(ComponentConfig componentConfig,
                                 ObjectMapperConfig objectMapperConfig,
                                 ArduinoQueueConfig arduinoQueueConfig) {
        this.arduinoQueueConfig = arduinoQueueConfig;
        this.componentConfig = componentConfig;
        this.objectMapperConfig = objectMapperConfig;
    }

    public ComponentConfig getComponentConfig() {
        return componentConfig;
    }

    public ObjectMapperConfig getObjectMapperConfig() {
        return objectMapperConfig;
    }

    public ArduinoQueueConfig getArduinoQueueConfig() {
        return arduinoQueueConfig;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private static final String EVENTS_PACKAGE = "com.cegeka.xparduino.event.impl";
        private static final String COMMANDS_PACKAGE = "com.cegeka.xparduino.command.impl";

        private ComponentConfig componentConfig = new ComponentConfig.Builder().build();
        private ObjectMapperConfig objectMapperConfig = new ObjectMapperConfig(EVENTS_PACKAGE, COMMANDS_PACKAGE);
        private ArduinoQueueConfig arduinoQueueConfig = new StubQueueConfig();

        public Builder withComponents(ComponentConfig config) {
            this.componentConfig = config;
            return this;
        }

        public Builder withArduinoQueue(ArduinoQueueConfig config) {
            this.arduinoQueueConfig = config;
            return this;
        }

        public ArduinoConfiguration build() {
            return new ArduinoConfiguration(componentConfig, objectMapperConfig, arduinoQueueConfig);
        }

    }

}
