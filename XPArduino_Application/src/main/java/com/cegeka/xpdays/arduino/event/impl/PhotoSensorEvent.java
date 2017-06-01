package com.cegeka.xpdays.arduino.event.impl;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventDeserializer;
import com.cegeka.xpdays.arduino.event.EventMapping;
import com.cegeka.xpdays.arduino.event.dispatch.SerializedEvent;

import static com.cegeka.xpdays.arduino.event.EventCode.PHOTO_SENSOR_EVENT;

@EventMapping(value = PHOTO_SENSOR_EVENT, mapper = PhotoSensorEvent.PhotoSensorEventDeserializer.class)
public class PhotoSensorEvent extends Event {

    private final int signal;

    public PhotoSensorEvent(int pin, int signal) {
        super(pin);
        this.signal = signal;
    }

    public int getSignal() {
        return signal;
    }

    public static class PhotoSensorEventDeserializer implements EventDeserializer<PhotoSensorEvent> {

        @Override
        public PhotoSensorEvent deserialize(SerializedEvent event) {
            int pin = event.getComponent().getPin();
            int signal = Integer.parseInt(event.getBody());
            return new PhotoSensorEvent(pin, signal);
        }
    }
}
