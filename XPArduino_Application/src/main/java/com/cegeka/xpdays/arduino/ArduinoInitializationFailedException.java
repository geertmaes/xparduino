package com.cegeka.xpdays.arduino;

public class ArduinoInitializationFailedException extends RuntimeException {

    public ArduinoInitializationFailedException(String message) {
        super(message);
    }

    public ArduinoInitializationFailedException(Throwable cause) {
        super(cause);
    }
}
