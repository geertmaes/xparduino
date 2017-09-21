package com.cegeka.xparduino.event.impl.photosensor;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventMapping;

import static com.cegeka.xparduino.event.EventCode.PHOTO_SENSOR_EVENT;

@EventMapping(
        code = PHOTO_SENSOR_EVENT,
        serializer = PhotoSensorEventSerializer.class,
        deserializer = PhotoSensorEventDeserializer.class)
public class PhotoSensorEvent implements Event {

    private final ComponentPin pin;
    private final int signal;

    public PhotoSensorEvent(ComponentPin pin, int signal) {
        this.pin = pin;
        this.signal = signal;
    }

    @Override
    public ComponentPin getPin() {
        return pin;
    }

    public int getSignal() {
        return signal;
    }
}
