package com.cegeka.xpdays.arduino.state.impl;

import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.state.ComponentState;

public class InfraredState extends ComponentState {

    private boolean emitting;

    public InfraredState(int pin) {
        super(pin);
    }

    public void setEmitting(boolean emitting) {
        this.emitting = emitting;
    }

    public boolean isEmitting() {
        return emitting;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.INFRARED_EMITTER;
    }
}
