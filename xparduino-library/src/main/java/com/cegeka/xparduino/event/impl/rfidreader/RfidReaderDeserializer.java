package com.cegeka.xparduino.event.impl.rfidreader;

import com.cegeka.xparduino.event.deserialize.EventDeserializer;
import com.cegeka.xparduino.event.serialize.SerializedEvent;
import com.cegeka.xparduino.event.deserialize.EventDeserializerMapping;

@EventDeserializerMapping(RfidReaderEvent.class)
public class RfidReaderDeserializer implements EventDeserializer<RfidReaderEvent> {

    @Override
    public RfidReaderEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        return new RfidReaderEvent(pin, event.body());
    }
}