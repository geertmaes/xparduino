package com.cegeka.xparduino.event.impl.infrared;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.event.EventDataSerializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

import static com.cegeka.xparduino.component.ComponentType.INFRARED_EMITTER;
import static com.cegeka.xparduino.event.EventCode.INFRARED_EMITTER_EVENT;

public class InfraredEventSerializer implements EventDataSerializer<InfraredEvent> {

    @Override
    public SerializedEvent serialize(InfraredEvent event) {
        String body = event.isEmitting() ? "true": "false";
        return new SerializedEvent(INFRARED_EMITTER_EVENT, body, new Component(event.getPin(), INFRARED_EMITTER));
    }
}