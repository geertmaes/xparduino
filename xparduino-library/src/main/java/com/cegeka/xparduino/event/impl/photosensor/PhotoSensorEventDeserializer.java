package com.cegeka.xparduino.event.impl.photosensor;

import com.cegeka.xparduino.event.EventDataDeserializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

public class PhotoSensorEventDeserializer implements EventDataDeserializer<PhotoSensorEvent> {

    @Override
    public PhotoSensorEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        int signal = Integer.parseInt(event.eventBody());
        return new PhotoSensorEvent(pin, signal);
    }
}