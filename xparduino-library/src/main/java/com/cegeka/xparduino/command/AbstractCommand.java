package com.cegeka.xparduino.command;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.component.ComponentType;

public abstract class AbstractCommand implements Command {

    private final ComponentPin pin;

    protected AbstractCommand(ComponentPin pin) {
        this.pin = pin;
    }

    public ComponentPin pin() {
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
