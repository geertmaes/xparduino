package com.cegeka.xparduino.event.impl.obstaclesensor;

import com.cegeka.xparduino.event.deserialize.EventDeserializer;
import com.cegeka.xparduino.event.serialize.SerializedEvent;
import com.cegeka.xparduino.event.deserialize.EventDeserializerMapping;

@EventDeserializerMapping(ObstacleSensorEvent.class)
public class ObstacleSensorDeserializer implements EventDeserializer<ObstacleSensorEvent> {

    @Override
    public ObstacleSensorEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        int signal = Integer.parseInt(event.body());
        return new ObstacleSensorEvent(pin, signal == 0);
    }
}