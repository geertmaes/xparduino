package com.cegeka.xpdays.arduino.event.impl.baseled;

import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializer;
import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializerMapping;
import com.cegeka.xpdays.arduino.event.dispatch.SerializedEvent;

@EventDeserializerMapping(BaseLedEvent.class)
public class BaseLedEventDeserializer implements EventDeserializer<BaseLedEvent> {

    @Override
    public BaseLedEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        boolean emitting = Boolean.parseBoolean(event.body());
        return new BaseLedEvent(pin, emitting);
    }
}