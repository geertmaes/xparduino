package com.cegeka.xparduino.queue.serialport;

public class SerialPortInitializationFailedException extends RuntimeException {

    public SerialPortInitializationFailedException(String message) {
        super(message);
    }

    public SerialPortInitializationFailedException(Throwable cause) {
        super(cause);
    }

}
