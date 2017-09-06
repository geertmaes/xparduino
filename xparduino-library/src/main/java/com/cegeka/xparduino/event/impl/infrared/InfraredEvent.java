package com.cegeka.xparduino.event.impl.infrared;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventMapping;

import static com.cegeka.xparduino.event.EventCode.INFRARED_EMITTER_EVENT;

@EventMapping(
        code = INFRARED_EMITTER_EVENT,
        serializer = InfraredEventSerializer.class,
        deserializer = InfraredEventDeserializer.class)
public class InfraredEvent implements Event {

    private final ComponentPin pin;
    private final boolean emitting;

    public InfraredEvent(ComponentPin pin, boolean emitting) {
        this.pin = pin;
        this.emitting = emitting;
    }

    @Override
    public ComponentPin getPin() {
        return pin;
    }

    public boolean isEmitting() {
        return emitting;
    }
}
