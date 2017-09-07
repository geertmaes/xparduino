package com.cegeka.xparduino.state.change;

import com.cegeka.xparduino.state.component.ComponentState;

@FunctionalInterface
public interface StateChangeListener<T extends ComponentState> {

    void onChange(T previousState, T currentState);
}
