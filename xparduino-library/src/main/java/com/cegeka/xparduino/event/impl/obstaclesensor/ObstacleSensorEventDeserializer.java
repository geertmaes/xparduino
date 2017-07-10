package com.cegeka.xparduino.event.impl.obstaclesensor;

import com.cegeka.xparduino.event.EventDeserializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

public class ObstacleSensorEventDeserializer implements EventDeserializer<ObstacleSensorEvent> {

    @Override
    public ObstacleSensorEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        int signal = Integer.parseInt(event.eventBody());
        return new ObstacleSensorEvent(pin, signal == 0);
    }
}