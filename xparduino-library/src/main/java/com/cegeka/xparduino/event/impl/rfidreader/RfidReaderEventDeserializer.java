package com.cegeka.xparduino.event.impl.rfidreader;

import com.cegeka.xparduino.event.EventDeserializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

public class RfidReaderEventDeserializer implements EventDeserializer<RfidReaderEvent> {

    @Override
    public RfidReaderEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        return new RfidReaderEvent(pin, event.eventBody());
    }
}