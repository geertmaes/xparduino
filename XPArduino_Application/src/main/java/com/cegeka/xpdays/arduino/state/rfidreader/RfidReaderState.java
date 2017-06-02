package com.cegeka.xpdays.arduino.state.rfidreader;

import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.state.ComponentState;

public class RfidReaderState extends ComponentState {

    private String tagId;

    public RfidReaderState(int pin) {
        super(pin);
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }


    @Override
    public ComponentType getComponentType() {
        return ComponentType.RFID_READER;
    }

}
