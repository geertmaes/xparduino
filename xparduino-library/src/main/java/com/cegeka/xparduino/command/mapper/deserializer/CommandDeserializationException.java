package com.cegeka.xparduino.command.mapper.deserializer;

public class CommandDeserializationException extends RuntimeException {

    public CommandDeserializationException(Throwable cause) {
        super(cause);
    }

    public CommandDeserializationException(String message) {
        super(message);
    }
}
