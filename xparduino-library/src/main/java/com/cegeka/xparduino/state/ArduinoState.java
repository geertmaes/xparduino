package com.cegeka.xparduino.state;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.state.component.ComponentState;
import com.cegeka.xparduino.state.component.ComponentStateFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
import static java.util.stream.Collectors.toMap;

public class ArduinoState {

    private static final String NO_COMPONENT_CONFIGURED = "No component configured on pin (%d)";
    private static final String COMPONENT_PIN_MISMATCH = "Component (%s) is configured on pin (%d), could not be (%s)";

    private final ComponentStateFactory stateFactory = new ComponentStateFactory();

    private final Map<Integer, ComponentType> components;
    private final Map<Integer, ComponentState> componentStates;

    public ArduinoState(Set<Component> components) {
        this.components = toComponentMapping(components);
        this.componentStates = toComponentStateMapping(components);
    }

    public Collection<ComponentState> getComponentStates() {
        return componentStates.values();
    }

    public <T extends ComponentState> T getState(int pin, Class<T> stateClass) {
        validatePin(pin);

        ComponentState state = componentStates.get(pin);
        validateComponentOnPin(pin, state.getComponentType());

        return stateClass.cast(state);
    }

    public void validatePin(int pin) {
        if (!components.containsKey(pin)) {
            throw new ArduinoStateException(format(NO_COMPONENT_CONFIGURED, pin));
        }
    }

    public void validateComponentOnPin(int pin, ComponentType actual) {
        ComponentType expected = components.get(pin);

        if (actual != expected) {
            throw new ArduinoStateException(format(COMPONENT_PIN_MISMATCH, expected, pin, actual));
        }
    }

    private Map<Integer, ComponentType> toComponentMapping(Set<Component> components) {
        return components.stream()
                .collect(toMap(Component::getPin, Component::getType));
    }

    private Map<Integer, ComponentState> toComponentStateMapping(Set<Component> components) {
        return components.stream()
                .collect(toMap(Component::getPin, stateFactory::create));
    }

}
