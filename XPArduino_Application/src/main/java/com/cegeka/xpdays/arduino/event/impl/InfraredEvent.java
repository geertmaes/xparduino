package com.cegeka.xpdays.arduino.event.impl;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventDeserializer;
import com.cegeka.xpdays.arduino.event.EventMapping;
import com.cegeka.xpdays.arduino.event.dispatch.SerializedEvent;

import static com.cegeka.xpdays.arduino.event.EventCode.INFRA_LED_EVENT;

@EventMapping(value = INFRA_LED_EVENT, mapper = InfraredEvent.InfraredOnEventDeserializer.class)
public class InfraredEvent extends Event {

    private final boolean emitting;

    public InfraredEvent(int pin, boolean emitting) {
        super(pin);
        this.emitting = emitting;
    }

    public boolean isEmitting() {
        return emitting;
    }

    public static class InfraredOnEventDeserializer implements EventDeserializer<InfraredEvent> {

        @Override
        public InfraredEvent deserialize(SerializedEvent event) {
            int pin = event.getComponent().getPin();
            boolean emitting = Boolean.parseBoolean(event.getBody());
            return new InfraredEvent(pin, emitting);
        }
    }
}
