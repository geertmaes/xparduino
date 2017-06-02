package com.cegeka.xpdays.arduino.state.impl;

import com.cegeka.xpdays.arduino.event.impl.BaseLedEvent;
import com.cegeka.xpdays.arduino.event.impl.ObstacleSensorEvent;
import com.cegeka.xpdays.arduino.state.ComponentStateListener;

@SuppressWarnings("unused")
public class ObstacleStateListener implements ComponentStateListener<ObstacleSensorState> {

    private ObstacleSensorState state;

    public ObstacleStateListener(int pin) {
        this.state = new ObstacleSensorState(pin);
    }

    public void on(ObstacleSensorEvent event) {
        if (event.getPin() == state.getPin()) {
            state.setBlocked(event.isBlocked());
        }
    }

    @Override
    public ObstacleSensorState getState() {
        return state;
    }
}
