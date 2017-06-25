package com.cegeka.xparduino.event.impl.infrared;

import com.cegeka.xparduino.event.deserialize.EventDeserializer;
import com.cegeka.xparduino.event.serialize.SerializedEvent;
import com.cegeka.xparduino.event.deserialize.EventDeserializerMapping;

@EventDeserializerMapping(InfraredEvent.class)
public class InfraredOnEventDeserializer implements EventDeserializer<InfraredEvent> {

    @Override
    public InfraredEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        boolean emitting = Boolean.parseBoolean(event.body());
        return new InfraredEvent(pin, emitting);
    }
}