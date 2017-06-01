package com.cegeka.xpdays.arduino.communication;

import com.cegeka.xpdays.arduino.event.dispatch.EventListener;

import java.io.Closeable;

public interface EventChannel extends Closeable {

    void registerEventListener(EventListener listener);
}
