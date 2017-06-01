package com.cegeka.xpdays.arduino.state;

import com.cegeka.xpdays.arduino.event.dispatch.EventListener;

public interface ComponentStateListener<T extends ComponentState> extends EventListener {

    T getState();
}
