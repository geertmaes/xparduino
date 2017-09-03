package com.cegeka.xparduino.event;

import com.cegeka.xparduino.domain.Direction;
import com.cegeka.xparduino.event.impl.baseled.BaseLedEvent;
import com.cegeka.xparduino.event.impl.obstaclesensor.ObstacleSensorEvent;
import com.cegeka.xparduino.event.impl.photosensor.PhotoSensorEvent;
import com.cegeka.xparduino.event.impl.trackswitch.TrackSwitchEvent;

import static com.cegeka.xparduino.component.ComponentTestConstants.PIN_1;

public class EventTestConstants {

    public static BaseLedEvent baseLedEvent(boolean emitting) {
        return new BaseLedEvent(PIN_1, emitting);
    }

    public static PhotoSensorEvent photoSensorEvent(int signal) {
        return new PhotoSensorEvent(PIN_1, signal);
    }

    public static ObstacleSensorEvent obstacleSensorEvent(boolean blocked) {
        return new ObstacleSensorEvent(PIN_1, blocked);
    }

    public static TrackSwitchEvent trackSwitchEvent(Direction direction) {
        return new TrackSwitchEvent(PIN_1, direction);
    }

}
