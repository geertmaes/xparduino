package com.cegeka.xparduino.event.impl.baseled;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventMapping;

import static com.cegeka.xparduino.event.EventCode.BASE_LED_EVENT;

@EventMapping(
        code = BASE_LED_EVENT,
        serializer = BaseLedEventSerializer.class,
        deserializer = BaseLedEventDeserializer.class)
public class BaseLedEvent implements Event {

    private final ComponentPin pin;
    private final boolean emitting;

    public BaseLedEvent(ComponentPin pin, boolean emitting) {
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
