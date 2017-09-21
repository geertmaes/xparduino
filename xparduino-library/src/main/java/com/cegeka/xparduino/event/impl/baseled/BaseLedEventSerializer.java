package com.cegeka.xparduino.event.impl.baseled;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.event.EventDataSerializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

import static com.cegeka.xparduino.component.ComponentType.BASE_LED;
import static com.cegeka.xparduino.event.EventCode.BASE_LED_EVENT;

public class BaseLedEventSerializer implements EventDataSerializer<BaseLedEvent> {

    @Override
    public SerializedEvent serialize(BaseLedEvent event) {
        String body = event.isEmitting() ? "true": "false";
        return new SerializedEvent(BASE_LED_EVENT, body, new Component(event.getPin(), BASE_LED));
    }
}