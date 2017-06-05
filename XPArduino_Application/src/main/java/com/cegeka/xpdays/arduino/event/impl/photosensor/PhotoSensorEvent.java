package com.cegeka.xpdays.arduino.event.impl.photosensor;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventMapping;

import static com.cegeka.xpdays.arduino.event.EventCode.PHOTO_SENSOR_EVENT;

@EventMapping(PHOTO_SENSOR_EVENT)
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
