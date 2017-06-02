package com.cegeka.xpdays.arduino.state.impl;

import com.cegeka.xpdays.arduino.event.impl.BaseLedEvent;
import com.cegeka.xpdays.arduino.event.impl.PhotoSensorEvent;
import com.cegeka.xpdays.arduino.state.ComponentStateListener;

@SuppressWarnings("unused")
public class PhotoSensorStateListener implements ComponentStateListener<PhotoSensorState> {

    private PhotoSensorState state;

    public PhotoSensorStateListener(int pin) {
        this.state = new PhotoSensorState(pin);
    }

    public void on(PhotoSensorEvent event) {
        if (event.getPin() == state.getPin()) {
            state.setSignal(event.getSignal());
        }
    }

    @Override
    public PhotoSensorState getState() {
        return state;
    }
}
