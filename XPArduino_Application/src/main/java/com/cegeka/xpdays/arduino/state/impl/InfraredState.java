package com.cegeka.xpdays.arduino.state.impl;

import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.event.Handle;
import com.cegeka.xpdays.arduino.event.impl.infrared.InfraredEvent;
import com.cegeka.xpdays.arduino.state.ComponentState;

public class InfraredState extends ComponentState<InfraredState> {

    private boolean emitting;

    public InfraredState(int pin) {
        super(pin);
    }

    @Handle
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

        InfraredState that = (InfraredState) o;

        return emitting == that.emitting;
    }

    @Override
    public int hashCode() {
        return (emitting ? 1 : 0);
    }
}
