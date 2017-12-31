package com.cegeka.xparduino.state;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.state.component.ComponentState;
import com.cegeka.xparduino.state.component.ComponentStateFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class ArduinoState {

    static final String NO_COMPONENT_CONFIGURED = "No component configured on pin (%s)";
    static final String COMPONENT_PIN_MISMATCH = "Component (%s) is configured on pin (%s), could not be (%s)";
    static final String STATE_PIN_MISMATCH = "State (%s) is configured on pin (%s), could not be (%s)";

    private final ComponentStateFactory stateFactory = new ComponentStateFactory();

    private final Map<ComponentPin, ComponentType> components;
    private final Map<ComponentPin, ComponentState> componentStates;

    public ArduinoState(Set<Component> components) {
        this.components = toComponentMapping(components);
        this.componentStates = toComponentStateMapping(components);
    }

    public Collection<ComponentState> getComponentStates() {
        return componentStates.values();
    }

    public Collection<ComponentState> getComponentStates(ComponentType type) {
        return components.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == type)
                .map(entry -> componentStates.get(entry.getKey()))
                .collect(toSet());
    }

    public <T extends ComponentState> T getState(ComponentPin pin, Class<T> stateClass) {
        validatePin(pin);

        ComponentState state = componentStates.get(pin);

        if (! state.getClass().isAssignableFrom(stateClass)) {
            throw new ArduinoStateException(format(STATE_PIN_MISMATCH, state.getClass(), pin, stateClass));
        }

        return stateClass.cast(state);
    }

    public void validatePin(ComponentPin pin) {
        if (!components.containsKey(pin)) {
            throw new ArduinoStateException(format(NO_COMPONENT_CONFIGURED, pin));
        }
    }

    public void validateComponentOnPin(ComponentPin pin, ComponentType actual) {
        validatePin(pin);

        ComponentType expected = components.get(pin);

        if (actual != expected) {
            throw new ArduinoStateException(format(COMPONENT_PIN_MISMATCH, expected, pin, actual));
        }
    }

    private Map<ComponentPin, ComponentType> toComponentMapping(Set<Component> components) {
        return components.stream()
                .collect(toMap(Component::getPin, Component::getType));
    }

    private Map<ComponentPin, ComponentState> toComponentStateMapping(Set<Component> components) {
        return components.stream()
                .collect(toMap(Component::getPin, stateFactory::create));
    }
}
