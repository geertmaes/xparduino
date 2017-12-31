package com.cegeka.xparduino.event;

import com.cegeka.xparduino.domain.Direction;
import com.cegeka.xparduino.event.impl.baseled.BaseLedEvent;
import com.cegeka.xparduino.event.impl.infrared.InfraredEvent;
import com.cegeka.xparduino.event.impl.obstaclesensor.ObstacleSensorEvent;
import com.cegeka.xparduino.event.impl.photosensor.PhotoSensorEvent;
import com.cegeka.xparduino.event.impl.rfidreader.RfidReaderEvent;
import com.cegeka.xparduino.event.impl.trackswitch.TrackSwitchEvent;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;

public class EventTestConstants {

    public static BaseLedEvent baseLedEvent(boolean emitting) {
        return new BaseLedEvent(DIGITAL_0, emitting);
    }

    public static PhotoSensorEvent photoSensorEvent(int signal) {
        return new PhotoSensorEvent(DIGITAL_0, signal);
    }

    public static ObstacleSensorEvent obstacleSensorEvent(boolean blocked) {
        return new ObstacleSensorEvent(DIGITAL_0, blocked);
    }

    public static InfraredEvent infraredEvent(boolean emitting) {
        return new InfraredEvent(DIGITAL_0, emitting);
    }

    public static RfidReaderEvent rfidReaderEvent(String tagId) {
        return new RfidReaderEvent(DIGITAL_0, tagId);
    }

    public static TrackSwitchEvent trackSwitchEvent(Direction direction) {
        return new TrackSwitchEvent(DIGITAL_0, direction);
    }

}
