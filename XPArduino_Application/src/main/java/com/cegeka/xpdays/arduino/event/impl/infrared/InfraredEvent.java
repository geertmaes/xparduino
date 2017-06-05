package com.cegeka.xpdays.arduino.event.impl.infrared;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventMapping;

import static com.cegeka.xpdays.arduino.event.EventCode.INFRARED_EMITTER_EVENT;

@EventMapping(INFRARED_EMITTER_EVENT)
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
