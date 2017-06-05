package com.cegeka.xpdays.arduino.state.impl;

import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.event.impl.baseled.BaseLedEvent;
import com.cegeka.xpdays.arduino.state.ComponentState;

public class BaseLedState extends ComponentState<BaseLedState> {

    private boolean emitting;

    public BaseLedState(int pin) {
        super(pin);
    }

    public void on(BaseLedEvent event) {
        if (event.getPin() == getPin()) {
            emitting = event.isEmitting();
            triggerStateChange();
        }
    }
    public boolean isEmitting() {
        return emitting;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.BASE_LED;
    }

    @Override
    public BaseLedState copy() {
        BaseLedState state = new BaseLedState(getPin());
        state.emitting = emitting;
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseLedState that = (BaseLedState) o;

        return emitting == that.emitting;
    }

    @Override
    public int hashCode() {
        return (emitting ? 1 : 0);
    }
}
