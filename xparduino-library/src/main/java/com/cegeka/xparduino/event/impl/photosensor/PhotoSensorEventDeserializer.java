package com.cegeka.xparduino.event.impl.photosensor;

import com.cegeka.xparduino.event.deserialize.EventDeserializer;
import com.cegeka.xparduino.event.serialize.SerializedEvent;
import com.cegeka.xparduino.event.deserialize.EventDeserializerMapping;

@EventDeserializerMapping(PhotoSensorEvent.class)
public class PhotoSensorEventDeserializer implements EventDeserializer<PhotoSensorEvent> {

    @Override
    public PhotoSensorEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        int signal = Integer.parseInt(event.body());
        return new PhotoSensorEvent(pin, signal);
    }
}