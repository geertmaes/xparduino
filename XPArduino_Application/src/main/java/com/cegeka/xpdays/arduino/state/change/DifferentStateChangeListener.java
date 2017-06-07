package com.cegeka.xpdays.arduino.state.change;

import com.cegeka.xpdays.arduino.state.ComponentState;

public class DifferentStateChangeListener<T extends ComponentState<T>>
        extends StateChangeDecorator<T> {

    public static <T extends ComponentState<T>> StateChangeListener<T> withDifferent(StateChangeListener<T> delegate) {
        return new DifferentStateChangeListener<>(delegate);
    }

    private T previousState = null;

    private DifferentStateChangeListener(StateChangeListener<T> delegate) {
        super(delegate);
    }

    @Override
    public void onChange(T state) {
        if (previousState == null || !state.equals(previousState)) {
            previousState = state;
            delegate().onChange(state);
        }
    }
}
