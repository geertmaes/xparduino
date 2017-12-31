package com.cegeka.xparduino.state.component.impl;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.event.impl.baseled.BaseLedEvent;
import com.cegeka.xparduino.state.component.ComponentState;

import static com.cegeka.xparduino.component.ComponentType.BASE_LED;

public class BaseLedState extends ComponentState<BaseLedState> {

    private boolean emitting;

    public BaseLedState(ComponentPin pin) {
        super(pin);
    }

    public void on(BaseLedEvent event) {
        emitting = event.isEmitting();
    }

    public boolean isEmitting() {
        return emitting;
    }

    @Override
    public ComponentType getComponentType() {
        return BASE_LED;
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
        if (!super.equals(o)) return false;

        BaseLedState that = (BaseLedState) o;

        return emitting == that.emitting;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (emitting ? 1 : 0);
        return result;
    }
}
