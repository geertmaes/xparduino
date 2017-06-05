package com.cegeka.xpdays.arduino.event.impl.rfidreader;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventMapping;

import static com.cegeka.xpdays.arduino.event.EventCode.RFID_READER_EVENT;

@EventMapping(RFID_READER_EVENT)
public class RfidReaderEvent implements Event {

    private final int pin;
    private final String tagId;

    public RfidReaderEvent(int pin, String tagId) {
        this.pin = pin;
        this.tagId = tagId;
    }

    @Override
    public int getPin() {
        return pin;
    }

    public String getTagId() {
        return tagId;
    }
}
