package com.cegeka.xpdays.arduino.event.impl.obstaclesensor;

import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializer;
import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializerMapping;
import com.cegeka.xpdays.arduino.event.dispatch.SerializedEvent;

@EventDeserializerMapping(ObstacleSensorEvent.class)
public class ObstacleSensorDeserializer implements EventDeserializer<ObstacleSensorEvent> {

    @Override
    public ObstacleSensorEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        int signal = Integer.parseInt(event.body());
        return new ObstacleSensorEvent(pin, signal == 0);
    }
}