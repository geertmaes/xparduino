package com.cegeka.xparduino.event;

import com.cegeka.xparduino.event.impl.baseled.BaseLedEvent;

import static com.cegeka.xparduino.component.ComponentTestConstants.PIN_1;

public class EventTestConstants {

    public static BaseLedEvent baseLedEvent(boolean emitting) {
        return new BaseLedEvent(PIN_1, emitting);
    }

}
