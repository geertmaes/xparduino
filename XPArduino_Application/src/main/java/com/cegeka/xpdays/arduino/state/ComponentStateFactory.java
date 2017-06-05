package com.cegeka.xpdays.arduino.state;

import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.state.impl.BaseLedState;
import com.cegeka.xpdays.arduino.state.impl.InfraredState;
import com.cegeka.xpdays.arduino.state.impl.ObstacleSensorState;
import com.cegeka.xpdays.arduino.state.impl.PhotoSensorState;
import com.cegeka.xpdays.arduino.state.impl.TrackSwitchState;

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
            default:
                throw new RuntimeException(format("No state listener configured for component (%s)", type));
        }
    }
}
