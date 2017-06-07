package com.cegeka.xpdays.arduino.state.change;

import com.cegeka.xpdays.arduino.state.ComponentState;

public class OnlyOnceStateChangeListener<T extends ComponentState<T>>
        implements StateChangeListener<T> {

    public static <T extends ComponentState<T>> StateChangeListener<T> onlyOnce(StateChangeListener<T> delegate) {
        return new OnlyOnceStateChangeListener<>(delegate);
    }

    private final StateChangeListener<T> delegate;
    private boolean called = false;

    private OnlyOnceStateChangeListener(StateChangeListener<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onChange(T state) {
        if (!called) {
            this.called = true;
            this.delegate.onChange(state);
        }
    }
}
