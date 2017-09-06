package com.cegeka.xparduino.event.impl.infrared;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.event.EventDataDeserializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

public class InfraredEventDeserializer implements EventDataDeserializer<InfraredEvent> {

    @Override
    public InfraredEvent deserialize(SerializedEvent event) {
        ComponentPin pin = event.component().getPin();
        boolean emitting = Boolean.parseBoolean(event.eventBody());
        return new InfraredEvent(pin, emitting);
    }
}