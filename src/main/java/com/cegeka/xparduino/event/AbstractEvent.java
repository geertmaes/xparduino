package com.cegeka.xparduino.event;

import com.cegeka.xparduino.component.ComponentPin;

import static java.util.Objects.requireNonNull;

public abstract class AbstractEvent implements Event {

    private final ComponentPin pin;

    protected AbstractEvent(ComponentPin pin) {
        requireNonNull(pin);
        this.pin = pin;
    }

    @Override
    public ComponentPin getPin() {
        return pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEvent that = (AbstractEvent) o;

        return pin == that.pin;
    }

    @Override
    public int hashCode() {
        return pin.hashCode();
    }

}
