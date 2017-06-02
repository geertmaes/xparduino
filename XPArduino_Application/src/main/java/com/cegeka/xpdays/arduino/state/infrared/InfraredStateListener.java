package com.cegeka.xpdays.arduino.state.infrared;

import com.cegeka.xpdays.arduino.event.impl.InfraredEvent;
import com.cegeka.xpdays.arduino.state.ComponentStateListener;

@SuppressWarnings("unused")
public class InfraredStateListener implements ComponentStateListener<InfraredState> {

    private InfraredState state;

    public InfraredStateListener(int pin) {
        this.state = new InfraredState(pin);
    }

    public void on(InfraredEvent event) {
        if (event.getPin() == state.getPin()) {
            state.setEmitting(event.isEmitting());
        }
    }

    @Override
    public InfraredState getState() {
        return state;
    }
}
