package com.cegeka.xparduino.state.component;

import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.state.change.StateChangeListener;

import java.util.LinkedList;
import java.util.List;

public abstract class ComponentState<T extends ComponentState> {

    private final int pin;
    private final List<StateChangeListener<T>> stateChangeListeners;

    protected ComponentState(int pin) {
        this.pin = pin;
        stateChangeListeners = new LinkedList<>();
    }

    void triggerStateChange() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentState<?> that = (ComponentState<?>) o;

        return pin == that.pin;
    }

    @Override
    public int hashCode() {
        return pin;
    }

}
