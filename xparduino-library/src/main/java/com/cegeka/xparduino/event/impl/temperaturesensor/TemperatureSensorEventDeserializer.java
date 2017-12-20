package com.cegeka.xparduino.event.impl.temperaturesensor;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.event.EventDataDeserializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

public class TemperatureSensorEventDeserializer implements EventDataDeserializer<TemperatureSensorEvent> {

    @Override
    public TemperatureSensorEvent deserialize(SerializedEvent event) {
        ComponentPin pin = event.component().getPin();
        Double degrees = Double.valueOf(event.eventBody());
        return new TemperatureSensorEvent(pin, degrees);
    }
}