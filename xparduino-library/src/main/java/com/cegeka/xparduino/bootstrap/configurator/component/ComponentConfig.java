package com.cegeka.xparduino.bootstrap.configurator.component;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.ComponentType;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class ComponentConfig {

    private final Set<Component> components;

    private ComponentConfig(Set<Component> components) {
        this.components = components;
    }

    Set<Component> getComponents() {
        return components;
    }

    public static class Builder {

        private Set<Component> components = newHashSet();

        public Builder withComponent(int pin, ComponentType type) {
            this.components.add(new Component(pin, type));
            return this;
        }

        public ComponentConfig build() {
            return new ComponentConfig(components);
        }

    }

}
