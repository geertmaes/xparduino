package com.cegeka.xparduino.state.change;

import com.cegeka.xparduino.state.component.ComponentState;

import java.util.Arrays;
import java.util.List;

public class MultipleStateChangeDecorator<T extends ComponentState>
        implements StateChangeListener<T> {

    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T extends ComponentState> StateChangeListener<T> withMultiple(StateChangeListener<T>... listeners) {
        return new MultipleStateChangeDecorator<>(listeners);
    }

    private final List<StateChangeListener<T>> listeners;

    @SafeVarargs
    @SuppressWarnings("varargs")
    private MultipleStateChangeDecorator(StateChangeListener<T>... listeners) {
        this.listeners = Arrays.asList(listeners);
    }

    @Override
    public void onChange(T state) {
        listeners.forEach(listener -> listener.onChange(state));
    }
}
