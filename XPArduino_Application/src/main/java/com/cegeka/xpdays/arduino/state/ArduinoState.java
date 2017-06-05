package com.cegeka.xpdays.arduino.state;

import com.cegeka.xpdays.arduino.ArduinoComponentPinMisMatchException;
import com.cegeka.xpdays.arduino.component.ComponentType;

import java.util.Collection;
import java.util.Map;

import static java.lang.String.format;
import static java.util.stream.Collectors.toMap;

public class ArduinoState {

    private static final String NO_COMPONENT_CONFIGURED = "No component configured on pin (%d)";
    private static final String COMPONENT_PIN_MISMATCH = "Expected component (%s) on pin (%d), but was (%s)";

    private final Map<Integer, ComponentType> components;
    private final Map<Integer, ComponentState> componentStates;
    private final ArduinoStateEventDispatcher stateEventDispatcher;

    public ArduinoState(Map<Integer, ComponentType> components) {
        this.components = components;
        this.componentStates = createComponentStates(components);
        this.stateEventDispatcher = new ArduinoStateEventDispatcher(this);
    }

    private Map<Integer, ComponentState> createComponentStates(Map<Integer, ComponentType> components) {
        return components.entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, this::createState));
    }

    private ComponentState createState(Map.Entry<Integer, ComponentType> entry) {
        return ComponentStateFactory.create(entry.getKey(), entry.getValue());
    }

    @SuppressWarnings("unchecked")
    public <T extends ComponentState> T getState(int pin, Class<T> stateClass) {
        validatePinConfigured(pin);

        ComponentState state = componentStates.get(pin);
        validatePinComponent(pin, state.getComponentType());

        return stateClass.cast(state);
    }

    public void validatePinConfigured(int pin) {
        if (!components.containsKey(pin)) {
            throw new ArduinoComponentPinMisMatchException(format(NO_COMPONENT_CONFIGURED, pin));
        }
    }

    public void validatePinComponent(int pin, ComponentType actual) {
        ComponentType expected = components.get(pin);

        if (expected != actual) {
            throw new ArduinoComponentPinMisMatchException(format(COMPONENT_PIN_MISMATCH, expected, pin, actual));
        }
    }

    public Collection<ComponentState> getComponentStates() {
        return componentStates.values();
    }

    public ArduinoStateEventDispatcher getStateEventDispatcher() {
        return stateEventDispatcher;
    }
}
