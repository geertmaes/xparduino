package com.cegeka.xpdays.arduino.communication;

import com.cegeka.xpdays.arduino.event.EventListener;

import java.io.Closeable;

public interface EventChannel extends Closeable {

    void send(String payload);

    void registerListener(EventListener listener);
}
