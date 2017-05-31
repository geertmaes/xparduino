package com.cegeka.xpdays.arduino.event.dispatch;

public class EventDeserializationException extends RuntimeException {

    public EventDeserializationException() {
    }

    public EventDeserializationException(String message) {
        super(message);
    }
}
