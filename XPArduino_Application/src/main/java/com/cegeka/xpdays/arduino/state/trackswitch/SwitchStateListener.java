package com.cegeka.xpdays.arduino.state.trackswitch;

import com.cegeka.xpdays.arduino.event.impl.SwitchEvent;
import com.cegeka.xpdays.arduino.state.ComponentStateListener;

@SuppressWarnings("unused")
public class SwitchStateListener implements ComponentStateListener<SwitchState> {

    private SwitchState state;

    public SwitchStateListener(int pin) {
        this.state = new SwitchState(pin);
    }

    public void on(SwitchEvent event) {
        if (event.getPin() == state.getPin()) {
            state.setDirection(event.getDirection());
        }
    }

    @Override
    public SwitchState getState() {
        return state;
    }
}
