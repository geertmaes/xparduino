package com.cegeka.xpdays.arduino.state.impl;

import com.cegeka.xpdays.arduino.event.impl.BaseLedEvent;
import com.cegeka.xpdays.arduino.state.ComponentStateListener;

@SuppressWarnings("unused")
public class BaseLedStateListener implements ComponentStateListener<BaseLedState> {

    private BaseLedState state;

    public BaseLedStateListener(int pin) {
        this.state = new BaseLedState(pin);
    }

    public void on(BaseLedEvent event) {
        if (event.getPin() == state.getPin()) {
            state.setEmitting(event.isEmitting());
        }
    }

    @Override
    public BaseLedState getState() {
        return state;
    }
}
