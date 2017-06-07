package com.cegeka.xpdays.arduino.state.change;

import com.cegeka.xpdays.arduino.state.ComponentState;

import java.util.Arrays;
import java.util.List;

public class MultipleStateChangeListener<T extends ComponentState> implements StateChangeListener<T> {

    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T extends ComponentState> StateChangeListener<T> withMultiple(StateChangeListener<T>... listeners) {
        return new MultipleStateChangeListener<>(listeners);
    }

    private final List<StateChangeListener<T>> listeners;

    @SafeVarargs
    @SuppressWarnings("varargs")
    private MultipleStateChangeListener(StateChangeListener<T>... listeners) {
        this.listeners = Arrays.asList(listeners);
    }

    @Override
    public void onChange(T state) {
        listeners.forEach(listener -> listener.onChange(state));
    }
}
