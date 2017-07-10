package com.cegeka.xparduino.event.impl.infrared;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventMapping;

import static com.cegeka.xparduino.event.EventCode.INFRARED_EMITTER_EVENT;

@EventMapping(
        code = INFRARED_EMITTER_EVENT,
        serializer = InfraredEventSerializer.class,
        deserializer = InfraredEventDeserializer.class)
public class InfraredEvent implements Event {

    private final int pin;
    private final boolean emitting;

    public InfraredEvent(int pin, boolean emitting) {
        this.pin = pin;
        this.emitting = emitting;
    }

    @Override
    public int getPin() {
        return pin;
    }

    public boolean isEmitting() {
        return emitting;
    }
}
