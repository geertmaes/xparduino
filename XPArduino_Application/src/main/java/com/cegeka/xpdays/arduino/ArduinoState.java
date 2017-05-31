package com.cegeka.xpdays.arduino;

import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.state.ComponentState;
import com.cegeka.xpdays.arduino.state.ComponentStateListener;
import com.cegeka.xpdays.arduino.state.ComponentStateListenerFactory;

import java.util.Collection;
import java.util.Map;

import static java.lang.String.format;
import static java.util.stream.Collectors.toMap;

public class ArduinoState {

    private static final String NO_COMPONENT_CONFIGURED = "No component configured on pin (%d)";
    private static final String COMPONENT_PIN_MISMATCH = "Expected component (%s) on pin (%d), but was (%s)";

    private final Map<Integer, ComponentType> components;
    private final Map<Integer, ComponentStateListener> componentStateListeners;

    ArduinoState(Map<Integer, ComponentType> components) {
        this.components = components;
        this.componentStateListeners = createComponentStateListeners(components);
    }

    private Map<Integer, ComponentStateListener> createComponentStateListeners(Map<Integer, ComponentType> components) {
        return components.entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, entry -> ComponentStateListenerFactory.create(entry.getKey(), entry.getValue())));
    }

    Collection<ComponentStateListener> getComponentStates() {
        return componentStateListeners.values();
    }

    @SuppressWarnings("unchecked")
    <T extends ComponentState> T getState(int pin, Class<T> stateClass) {
        validatePinConfigured(pin);

        ComponentState state = componentStateListeners.get(pin).getState();
        validatePinComponent(pin, state.getComponentType());

        return stateClass.cast(state);
    }

    void validatePinConfigured(int pin) {
        ComponentType expected = components.get(pin);

        if (expected == null) {
            throw new ArduinoComponentPinMisMatchException(format(NO_COMPONENT_CONFIGURED, pin));
        }
    }

    void validatePinComponent(int pin, ComponentType actual) {
        ComponentType expected = components.get(pin);

        if (expected != actual) {
            throw new ArduinoComponentPinMisMatchException(format(COMPONENT_PIN_MISMATCH, expected, pin, actual));
        }
    }
}
