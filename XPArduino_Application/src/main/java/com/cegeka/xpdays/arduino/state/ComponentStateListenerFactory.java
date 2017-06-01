package com.cegeka.xpdays.arduino.state;

import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.state.impl.BaseLedStateListener;
import com.cegeka.xpdays.arduino.state.impl.PhotoSensorStateListener;

import static java.lang.String.format;

public class ComponentStateListenerFactory {

    public static ComponentStateListener create(int pin, ComponentType type) {
        switch (type) {
            case BASE_LED:
                return new BaseLedStateListener(pin);
            case PHOTO_SENSOR:
                return new PhotoSensorStateListener(pin);
            default:
                throw new RuntimeException(format("No state listener configured for component (%s)", type));
        }
    }
}
