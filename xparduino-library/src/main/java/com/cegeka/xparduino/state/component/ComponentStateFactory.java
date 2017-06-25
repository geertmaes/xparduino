package com.cegeka.xparduino.state.component;

import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.state.ArduinoStateException;
import com.cegeka.xparduino.state.component.impl.*;

import static java.lang.String.format;

public class ComponentStateFactory {

    public static ComponentState createState(int pin, ComponentType type) {
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
                throw new ArduinoStateException(format("No state configured for component (%s)", type));
        }
    }
}
