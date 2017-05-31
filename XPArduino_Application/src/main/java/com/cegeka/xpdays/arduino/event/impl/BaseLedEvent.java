package com.cegeka.xpdays.arduino.event.impl;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventDeserializer;
import com.cegeka.xpdays.arduino.event.EventMapping;
import com.cegeka.xpdays.arduino.event.dispatch.SerializedEvent;

import static com.cegeka.xpdays.arduino.event.EventCode.BASE_LED_EVENT;

@EventMapping(value = BASE_LED_EVENT, mapper = BaseLedEvent.BaseLedOnEventDeserializer.class)
public class BaseLedEvent implements Event {

    private final boolean emitting;

    public BaseLedEvent(boolean emitting) {
        this.emitting = emitting;
    }

    public boolean isEmitting() {
        return emitting;
    }

    public static class BaseLedOnEventDeserializer implements EventDeserializer<BaseLedEvent> {

        @Override
        public BaseLedEvent deserialize(SerializedEvent event) {
            boolean emitting = Boolean.parseBoolean(event.getBody());
            return new BaseLedEvent(emitting);
        }
    }
}
