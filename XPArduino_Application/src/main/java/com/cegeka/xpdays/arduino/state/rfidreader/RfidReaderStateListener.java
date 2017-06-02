package com.cegeka.xpdays.arduino.state.rfidreader;

import com.cegeka.xpdays.arduino.event.impl.ObstacleSensorEvent;
import com.cegeka.xpdays.arduino.event.impl.RfidEvent;
import com.cegeka.xpdays.arduino.state.ComponentStateListener;

@SuppressWarnings("unused")
public class RfidReaderStateListener implements ComponentStateListener<RfidReaderState> {

    private RfidReaderState state;

    public RfidReaderStateListener(int pin) {
        this.state = new RfidReaderState(pin);
    }

    public void on(RfidEvent event) {
        if (event.getPin() == state.getPin()) {
            state.setTagId(event.getTagId());
        }
    }

    @Override
    public RfidReaderState getState() {
        return state;
    }
}
