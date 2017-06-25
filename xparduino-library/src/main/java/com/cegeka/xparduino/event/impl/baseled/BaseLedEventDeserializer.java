package com.cegeka.xparduino.event.impl.baseled;

import com.cegeka.xparduino.event.deserialize.EventDeserializer;
import com.cegeka.xparduino.event.deserialize.EventDeserializerMapping;
import com.cegeka.xparduino.event.serialize.SerializedEvent;

@EventDeserializerMapping(BaseLedEvent.class)
public class BaseLedEventDeserializer implements EventDeserializer<BaseLedEvent> {

    @Override
    public BaseLedEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        boolean emitting = Boolean.parseBoolean(event.body());
        return new BaseLedEvent(pin, emitting);
    }
}