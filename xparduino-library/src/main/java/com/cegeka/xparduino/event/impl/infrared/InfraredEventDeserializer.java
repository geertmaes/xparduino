package com.cegeka.xparduino.event.impl.infrared;

import com.cegeka.xparduino.event.EventDeserializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

public class InfraredEventDeserializer implements EventDeserializer<InfraredEvent> {

    @Override
    public InfraredEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        boolean emitting = Boolean.parseBoolean(event.eventBody());
        return new InfraredEvent(pin, emitting);
    }
}