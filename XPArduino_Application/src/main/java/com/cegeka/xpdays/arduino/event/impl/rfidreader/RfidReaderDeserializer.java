package com.cegeka.xpdays.arduino.event.impl.rfidreader;

import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializer;
import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializerMapping;
import com.cegeka.xpdays.arduino.event.dispatch.SerializedEvent;

@EventDeserializerMapping(RfidReaderEvent.class)
public class RfidReaderDeserializer implements EventDeserializer<RfidReaderEvent> {

    @Override
    public RfidReaderEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        return new RfidReaderEvent(pin, event.body());
    }
}