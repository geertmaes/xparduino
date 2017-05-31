package com.cegeka.xpdays.arduino.state;

import com.cegeka.xpdays.arduino.component.ComponentType;

public abstract class ComponentState {

    private final int pin;

    public ComponentState(int pin) {
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    public abstract ComponentType getComponentType();
}
