package com.cegeka.xpdays.arduino.communication;

public interface EventChannel {

    void onEvent(EventListener listener);
}
