package com.cegeka.xpdays.arduino.event.dispatch;

public class EventDispatchingException extends Exception {

    EventDispatchingException(String message) {
        super(message);
    }

    EventDispatchingException(Throwable cause) {
        super(cause);
    }
}
