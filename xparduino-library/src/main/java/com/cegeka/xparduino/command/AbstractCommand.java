package com.cegeka.xparduino.command;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.component.ComponentType;

import static java.util.Objects.requireNonNull;

public abstract class AbstractCommand implements Command {

    private final ComponentPin pin;

    protected AbstractCommand(ComponentPin pin) {
        requireNonNull(pin);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractCommand that = (AbstractCommand) o;

        return pin == that.pin;
    }

    @Override
    public int hashCode() {
        return pin.hashCode();
    }

}
