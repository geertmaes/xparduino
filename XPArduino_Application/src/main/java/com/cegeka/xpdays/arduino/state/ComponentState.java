package com.cegeka.xpdays.arduino.state;

import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.state.change.StateChangeListener;

import java.util.LinkedList;
import java.util.List;

public abstract class ComponentState<T extends ComponentState> {

    private final int pin;
    private final List<StateChangeListener<T>> stateChangeListeners;

    protected ComponentState(int pin) {
        this.pin = pin;
        stateChangeListeners = new LinkedList<>();
    }

    protected void triggerStateChange() {
        stateChangeListeners.forEach(listener -> listener.onChange(copy()));
    }

    public void onStateChange(StateChangeListener<T> listener) {
        stateChangeListeners.add(listener);
    }

    public int getPin() {
        return pin;
    }

    protected abstract T copy();

    public abstract ComponentType getComponentType();
}
