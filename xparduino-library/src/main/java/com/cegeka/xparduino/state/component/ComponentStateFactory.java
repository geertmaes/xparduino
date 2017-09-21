package com.cegeka.xparduino.state.component;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.state.ArduinoStateException;
import com.cegeka.xparduino.state.component.impl.*;
import com.google.common.collect.ImmutableMap;

import java.lang.reflect.Constructor;
import java.util.Map;

import static com.cegeka.xparduino.component.ComponentType.*;
import static java.lang.String.format;

public class ComponentStateFactory {

    private static final String NO_STATE_CONFIGURED
            = "No state configured for component type (%s)";
    private static final String INSTANTIATION_EXCEPTION
            = "Failed to instantiate component state (%s) with pin constructor";

    private static final Map<ComponentType, Class<? extends ComponentState<?>>> componentStateMapping
            = ImmutableMap.<ComponentType, Class<? extends ComponentState<?>>> builder()
            .put(BASE_LED, BaseLedState.class)
            .put(INFRARED_EMITTER, InfraredState.class)
            .put(OBSTACLE_SENSOR, ObstacleSensorState.class)
            .put(PHOTO_SENSOR, PhotoSensorState.class)
            .put(RFID_READER, RfidReaderState.class)
            .put(TRACK_SWITCH, TrackSwitchState.class)
            .build();

    public ComponentState create(Component component) {
        ComponentType type = component.getType();

        if (componentStateMapping.containsKey(type)) {
            return createComponentState(component.getPin(), componentStateMapping.get(type));
        }

        throw new ArduinoStateException(format(NO_STATE_CONFIGURED, type));
    }

    private ComponentState createComponentState(ComponentPin pin, Class<? extends ComponentState> stateClass) {
        try {
            Constructor<? extends ComponentState> constructorWithPin = stateClass.getConstructor(ComponentPin.class);
            return constructorWithPin.newInstance(pin);
        } catch (Exception e) {
            throw new ArduinoStateException(format(INSTANTIATION_EXCEPTION, stateClass.getSimpleName()));
        }
    }
}
