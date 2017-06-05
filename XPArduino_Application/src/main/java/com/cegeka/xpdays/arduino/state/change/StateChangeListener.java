package com.cegeka.xpdays.arduino.state.change;

import com.cegeka.xpdays.arduino.state.ComponentState;

@FunctionalInterface
public interface StateChangeListener<T extends ComponentState> {

    void onChange(T state);
}
