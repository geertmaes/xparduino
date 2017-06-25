package com.cegeka.xparduino.event.impl.infrared;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventCode;
import com.cegeka.xparduino.event.EventMapping;

@EventMapping(EventCode.INFRARED_EMITTER_EVENT)
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
