package com.cegeka.xpdays.arduino.event.deserialiser;

public class EventDeserializationException extends RuntimeException {

    public EventDeserializationException(String message) {
        super(message);
    }

    public EventDeserializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
