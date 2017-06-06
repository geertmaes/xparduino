package com.cegeka.xpdays.arduino.state;

import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.state.impl.*;

import static java.lang.String.format;

public class ComponentStateFactory {

    public static ComponentState create(int pin, ComponentType type) {
        switch (type) {
            case BASE_LED:
                return new BaseLedState(pin);
            case PHOTO_SENSOR:
                return new PhotoSensorState(pin);
            case OBSTACLE_SENSOR:
                return new ObstacleSensorState(pin);
            case INFRARED_EMITTER:
                return new InfraredState(pin);
            case TRACK_SWITCH:
                return new TrackSwitchState(pin);
            case RFID_READER:
                return new RfidReaderState(pin);
            default:
                throw new RuntimeException(format("No state configured for component (%s)", type));
        }
    }
}
