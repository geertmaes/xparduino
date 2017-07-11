package com.cegeka.xparduino.event.impl.baseled;

import com.cegeka.xparduino.event.EventDataDeserializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

public class BaseLedEventDeserializer implements EventDataDeserializer<BaseLedEvent> {

    @Override
    public BaseLedEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        boolean emitting = Boolean.parseBoolean(event.eventBody());
        return new BaseLedEvent(pin, emitting);
    }
}