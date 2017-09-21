package com.cegeka.xparduino.event.impl.rfidreader;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.event.EventDataSerializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

import static com.cegeka.xparduino.component.ComponentType.RFID_READER;
import static com.cegeka.xparduino.event.EventCode.RFID_READER_EVENT;

public class RfidReaderEventSerializer implements EventDataSerializer<RfidReaderEvent> {

    @Override
    public SerializedEvent serialize(RfidReaderEvent event) {
        String body = event.getTagId();
        return new SerializedEvent(RFID_READER_EVENT, body, new Component(event.getPin(), RFID_READER));
    }
}