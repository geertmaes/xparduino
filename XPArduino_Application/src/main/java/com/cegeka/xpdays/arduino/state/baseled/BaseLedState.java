package com.cegeka.xpdays.arduino.state.baseled;

import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.state.ComponentState;

public class BaseLedState extends ComponentState {

    private boolean emitting;

    public BaseLedState(int pin) {
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
        return ComponentType.BASE_LED;
    }
}
