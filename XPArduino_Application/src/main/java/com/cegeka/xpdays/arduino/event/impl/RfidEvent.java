package com.cegeka.xpdays.arduino.event.impl;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventDeserializer;
import com.cegeka.xpdays.arduino.event.EventMapping;
import com.cegeka.xpdays.arduino.event.dispatch.SerializedEvent;

import static com.cegeka.xpdays.arduino.event.EventCode.OBSTACLE_SENSOR_EVENT;
import static com.cegeka.xpdays.arduino.event.EventCode.RFID_EVENT;

@EventMapping(value = RFID_EVENT, mapper = RfidEvent.RfidReaderDeserializer.class)
public class RfidEvent extends Event {

    private final String tagId;

    public RfidEvent(int pin, String tagId) {
        super(pin);
        this.tagId = tagId;
    }

    public String getTagId() {
        return tagId;
    }

    public static class RfidReaderDeserializer implements EventDeserializer<RfidEvent> {

        @Override
        public RfidEvent deserialize(SerializedEvent event) {
            int pin = event.getComponent().getPin();
            return new RfidEvent(pin, event.getBody());
        }
    }
}
