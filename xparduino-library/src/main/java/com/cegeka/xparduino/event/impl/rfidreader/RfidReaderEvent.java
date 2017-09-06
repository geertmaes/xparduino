package com.cegeka.xparduino.event.impl.rfidreader;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventMapping;

import static com.cegeka.xparduino.event.EventCode.RFID_READER_EVENT;

@EventMapping(
        code = RFID_READER_EVENT,
        serializer = RfidReaderEventSerializer.class,
        deserializer = RfidReaderEventDeserializer.class)
public class RfidReaderEvent implements Event {

    private final ComponentPin pin;
    private final String tagId;

    public RfidReaderEvent(ComponentPin pin, String tagId) {
        this.pin = pin;
        this.tagId = tagId;
    }

    @Override
    public ComponentPin getPin() {
        return pin;
    }

    public String getTagId() {
        return tagId;
    }
}
