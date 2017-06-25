package com.cegeka.xparduino.state;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.event.EventListener;
import com.cegeka.xparduino.event.Handle;
import com.cegeka.xparduino.state.component.ComponentState;
import com.cegeka.xparduino.state.component.ComponentStateEventDispatcher;
import com.cegeka.xparduino.state.component.ComponentStateFactory;

import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
import static java.util.stream.Collectors.toMap;

public class ArduinoState implements EventListener {

    private static final String NO_COMPONENT_CONFIGURED = "No component configured dispatch pin (%d)";
    private static final String COMPONENT_PIN_MISMATCH = "Component (%s) is configured on pin (%d), could not be (%s)";

    private final Map<Integer, ComponentType> components;
    private final Map<Integer, ComponentState> componentStates;
    private final ComponentStateEventDispatcher stateEventDispatcher;

    public ArduinoState(Set<Component> components) {
        this.components = toComponentMapping(components);
        this.componentStates = toComponentStateMapping(components);
        this.stateEventDispatcher = new ComponentStateEventDispatcher(componentStates.values());
    }

    @Handle
    @SuppressWarnings("unused")
    public void on(Event event) {
        stateEventDispatcher.dispatch(event);
    }

    @SuppressWarnings("unchecked")
    public <T extends ComponentState> T getState(int pin, Class<T> stateClass) {
        validatePin(pin);

        ComponentState state = componentStates.get(pin);
        validatePinComponent(pin, state.getComponentType());

        return stateClass.cast(state);
    }

    public void validatePin(int pin) {
        if (!components.containsKey(pin)) {
            throw new ArduinoStateException(format(NO_COMPONENT_CONFIGURED, pin));
        }
    }

    public void validatePinComponent(int pin, ComponentType actual) {
        ComponentType expected = components.get(pin);

        if (expected != actual) {
            throw new ArduinoStateException(format(COMPONENT_PIN_MISMATCH, expected, pin, actual));
        }
    }

    private Map<Integer, ComponentType> toComponentMapping(Set<Component> components) {
        return components.stream()
                .collect(toMap(Component::getPin, Component::getType));
    }

    private Map<Integer, ComponentState> toComponentStateMapping(Set<Component> components) {
        return components.stream()
                .collect(toMap(Component::getPin, this::createState));
    }

    private ComponentState createState(Component component) {
        return ComponentStateFactory.createState(component.getPin(), component.getType());
    }
}
