package com.cegeka.xparduino.event.impl.rfidreader;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.event.EventDataDeserializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

public class RfidReaderEventDeserializer implements EventDataDeserializer<RfidReaderEvent> {

    @Override
    public RfidReaderEvent deserialize(SerializedEvent event) {
        ComponentPin pin = event.component().getPin();
        return new RfidReaderEvent(pin, event.eventBody());
    }
}