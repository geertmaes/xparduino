package com.cegeka.xparduino.event.impl.photosensor;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventMapping;

import static com.cegeka.xparduino.event.EventCode.PHOTO_SENSOR_EVENT;

@EventMapping(
        code = PHOTO_SENSOR_EVENT,
        serializer = PhotoSensorEventSerializer.class,
        deserializer = PhotoSensorEventDeserializer.class)
public class PhotoSensorEvent implements Event {

    private final int pin;
    private final int signal;

    public PhotoSensorEvent(int pin, int signal) {
        this.pin = pin;
        this.signal = signal;
    }

    @Override
    public int getPin() {
        return pin;
    }

    public int getSignal() {
        return signal;
    }
}
