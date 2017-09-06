package com.cegeka.xparduino.command;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.ComponentType;

public abstract class AbstractCommand implements Command {

    private final int pin;

    protected AbstractCommand(int pin) {
        this.pin = pin;
    }

    public int pin() {
        return pin;
    }

    @Override
    public abstract String getAction();

    protected abstract ComponentType getComponentType();

    @Override
    public Component getComponent() {
        return new Component(pin, getComponentType());
    }

}
