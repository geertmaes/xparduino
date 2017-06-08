package com.cegeka.xpdays.arduino.state.impl;

import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.event.Handle;
import com.cegeka.xpdays.arduino.event.impl.rfidreader.RfidReaderEvent;
import com.cegeka.xpdays.arduino.state.ComponentState;

public class RfidReaderState extends ComponentState<RfidReaderState> {

    private String tagId;

    public RfidReaderState(int pin) {
        super(pin);
    }

    @Handle
    public void on(RfidReaderEvent event) {
        tagId = event.getTagId();
    }

    public String getTagId() {
        return tagId;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.RFID_READER;
    }

    @Override
    public RfidReaderState copy() {
        RfidReaderState state = new RfidReaderState(getPin());
        state.tagId = tagId;
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RfidReaderState that = (RfidReaderState) o;

        return tagId != null ? tagId.equals(that.tagId) : that.tagId == null;
    }

    @Override
    public int hashCode() {
        return tagId != null ? tagId.hashCode() : 0;
    }
}
