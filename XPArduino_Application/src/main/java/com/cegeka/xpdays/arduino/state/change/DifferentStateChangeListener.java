package com.cegeka.xpdays.arduino.state.change;

import com.cegeka.xpdays.arduino.state.ComponentState;

public class DifferentStateChangeListener<T extends ComponentState<T>>
        implements StateChangeListener<T> {

    public static <T extends ComponentState<T>> StateChangeListener<T> onDifferent(StateChangeListener<T> delegate) {
        return new DifferentStateChangeListener<>(delegate);
    }

    private final StateChangeListener<T> delegate;
    private T previousState = null;

    private DifferentStateChangeListener(StateChangeListener<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onChange(T state) {
        if (previousState == null || !state.equals(previousState)) {
            this.previousState = state;
            this.delegate.onChange(state);
        }
    }
}
