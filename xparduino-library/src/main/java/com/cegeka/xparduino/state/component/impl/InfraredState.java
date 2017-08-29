package com.cegeka.xparduino.state.component.impl;

import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.event.impl.infrared.InfraredEvent;
import com.cegeka.xparduino.state.component.ComponentState;

public class InfraredState extends ComponentState<InfraredState> {

    private boolean emitting;

    public InfraredState(int pin) {
        super(pin);
    }

    public void on(InfraredEvent event) {
        emitting = event.isEmitting();
    }

    public boolean isEmitting() {
        return emitting;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.INFRARED_EMITTER;
    }

    @Override
    public InfraredState copy() {
        InfraredState state = new InfraredState(getPin());
        state.emitting = emitting;
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        InfraredState that = (InfraredState) o;

        return emitting == that.emitting;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (emitting ? 1 : 0);
        return result;
    }
}
