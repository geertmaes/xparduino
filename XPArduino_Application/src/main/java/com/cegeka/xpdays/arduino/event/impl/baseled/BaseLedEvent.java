package com.cegeka.xpdays.arduino.event.impl.baseled;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventMapping;

import static com.cegeka.xpdays.arduino.event.EventCode.BASE_LED_EVENT;

@EventMapping(BASE_LED_EVENT)
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
