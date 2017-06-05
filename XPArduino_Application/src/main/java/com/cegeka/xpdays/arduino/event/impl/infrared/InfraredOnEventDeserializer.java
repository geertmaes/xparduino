package com.cegeka.xpdays.arduino.event.impl.infrared;

import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializer;
import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializerMapping;
import com.cegeka.xpdays.arduino.event.dispatch.SerializedEvent;

@EventDeserializerMapping(InfraredEvent.class)
public class InfraredOnEventDeserializer implements EventDeserializer<InfraredEvent> {

    @Override
    public InfraredEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        boolean emitting = Boolean.parseBoolean(event.body());
        return new InfraredEvent(pin, emitting);
    }
}