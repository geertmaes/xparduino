package com.cegeka.xpdays.arduino.event.dispatch;

public class EventDispatcherStateException extends RuntimeException {

    public EventDispatcherStateException(String message) {
        super(message);
    }

    public EventDispatcherStateException(Throwable cause) {
        super(cause);
    }
}
