package com.cegeka.xpdays.arduino.monitor;

@FunctionalInterface
public interface MessageListener {

    void onMessage(String message);
}
