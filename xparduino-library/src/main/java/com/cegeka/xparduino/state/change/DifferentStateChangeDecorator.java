package com.cegeka.xparduino.state.change;

import com.cegeka.xparduino.state.component.ComponentState;

public class DifferentStateChangeDecorator<T extends ComponentState<T>>
        extends StateChangeDecorator<T> {

    public static <T extends ComponentState<T>> StateChangeListener<T> withDifferent(StateChangeListener<T> delegate) {
        return new DifferentStateChangeDecorator<>(delegate);
    }

    private T previousState = null;

    private DifferentStateChangeDecorator(StateChangeListener<T> delegate) {
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
