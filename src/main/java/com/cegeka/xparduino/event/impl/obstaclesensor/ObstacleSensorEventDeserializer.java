package com.cegeka.xparduino.event.impl.obstaclesensor;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.event.EventDataDeserializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

public class ObstacleSensorEventDeserializer implements EventDataDeserializer<ObstacleSensorEvent> {

    @Override
    public ObstacleSensorEvent deserialize(SerializedEvent event) {
        ComponentPin pin = event.component().getPin();
        int signal = Integer.parseInt(event.eventBody());
        return new ObstacleSensorEvent(pin, signal == 0);
    }
}