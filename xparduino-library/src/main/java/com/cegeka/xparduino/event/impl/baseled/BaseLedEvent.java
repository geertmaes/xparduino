package com.cegeka.xparduino.event.impl.baseled;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventCode;
import com.cegeka.xparduino.event.EventMapping;

@EventMapping(EventCode.BASE_LED_EVENT)
public class BaseLedEvent implements Event {

    private final int pin;
    private final boolean emitting;

    public BaseLedEvent(int pin, boolean emitting) {
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
