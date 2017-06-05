package com.cegeka.xpdays.arduino.event.impl.photosensor;

import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializer;
import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializerMapping;
import com.cegeka.xpdays.arduino.event.dispatch.SerializedEvent;

@EventDeserializerMapping(PhotoSensorEvent.class)
public class PhotoSensorEventDeserializer implements EventDeserializer<PhotoSensorEvent> {

    @Override
    public PhotoSensorEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        int signal = Integer.parseInt(event.body());
        return new PhotoSensorEvent(pin, signal);
    }
}