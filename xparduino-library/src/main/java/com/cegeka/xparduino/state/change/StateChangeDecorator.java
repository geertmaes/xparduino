package com.cegeka.xparduino.state.change;

import com.cegeka.xparduino.state.component.ComponentState;

public abstract class StateChangeDecorator<T extends ComponentState>
        implements StateChangeListener<T> {

    private final StateChangeListener<T> delegate;

    StateChangeDecorator(StateChangeListener<T> delegate) {
        this.delegate = delegate;
    }

    StateChangeListener<T> delegate() {
        return delegate;
    }

    @Override
    public abstract void onChange(T state);
}
