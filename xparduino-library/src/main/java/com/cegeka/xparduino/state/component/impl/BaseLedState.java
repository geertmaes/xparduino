package com.cegeka.xparduino.state.component.impl;

import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.event.Handle;
import com.cegeka.xparduino.state.component.ComponentState;
import com.cegeka.xparduino.event.impl.baseled.BaseLedEvent;

public class BaseLedState extends ComponentState<BaseLedState> {

    private boolean emitting;

    public BaseLedState(int pin) {
        super(pin);
    }

    @Handle
    public void on(BaseLedEvent event) {
        emitting = event.isEmitting();
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
