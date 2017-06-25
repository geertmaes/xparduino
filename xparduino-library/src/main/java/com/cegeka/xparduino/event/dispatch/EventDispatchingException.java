package com.cegeka.xparduino.event.dispatch;

public class EventDispatchingException extends Exception {

    EventDispatchingException(String message) {
        super(message);
    }

    EventDispatchingException(Throwable cause) {
        super(cause);
    }
}
