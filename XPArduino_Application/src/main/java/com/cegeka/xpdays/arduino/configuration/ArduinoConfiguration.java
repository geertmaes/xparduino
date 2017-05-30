package com.cegeka.xpdays.arduino.configuration;

import com.cegeka.xpdays.arduino.component.ComponentType;

import java.util.HashMap;
import java.util.Map;

public class ArduinoConfiguration {


    private final String portName;
    private final Map<Integer, ComponentType> components;

    private ArduinoConfiguration(String portName, Map<Integer, ComponentType> components) {
        this.portName = portName;
        this.components = components;
    }

    public String getPortName() {
        return portName;
    }

    public Map<Integer, ComponentType> getComponents() {
        return components;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String portName;
        private Map<Integer, ComponentType> components;

        private Builder() {
            components = new HashMap<>();
        }

        public Builder withPortName(String portName) {
            this.portName = portName;
            return this;
        }

        public Builder withComponent(int pin, ComponentType type) {
            this.components.put(pin, type);
            return this;
        }

        public ArduinoConfiguration build() {
            return new ArduinoConfiguration(portName, components);
        }
    }
}
