package com.cegeka.xparduino.event.impl.photosensor;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.event.EventDataSerializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

import static com.cegeka.xparduino.component.ComponentType.PHOTO_SENSOR;
import static com.cegeka.xparduino.event.EventCode.PHOTO_SENSOR_EVENT;

public class PhotoSensorEventSerializer implements EventDataSerializer<PhotoSensorEvent> {

    @Override
    public SerializedEvent serialize(PhotoSensorEvent event) {
        String body = String.valueOf(event.getSignal());
        return new SerializedEvent(PHOTO_SENSOR_EVENT, body, new Component(event.getPin(), PHOTO_SENSOR));
    }
}