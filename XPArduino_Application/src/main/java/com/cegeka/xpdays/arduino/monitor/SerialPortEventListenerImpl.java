package com.cegeka.xpdays.arduino.monitor;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class SerialPortEventListenerImpl implements SerialPortEventListener {

    private final Logger logger = LoggerFactory.getLogger(SerialPortEventListenerImpl.class);

    private final SerialPort port;
    private final Set<Consumer<String>> listeners;

    SerialPortEventListenerImpl(SerialPort port) {
        this.port = port;
        this.listeners = new HashSet<>();
    }

    public void addListener(Consumer<String> listener) {
        listeners.add(listener);
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (isValidEvent(event)) {
            String text = readFromPort(event);
            listeners.forEach(listener -> listener.accept(text));
        }
    }

    private boolean isValidEvent(SerialPortEvent event) {
        return event.isRXCHAR() && event.getEventValue() > 0;
    }

    private String readFromPort(SerialPortEvent event) {
        try {
            return this.port.readString(event.getEventValue());
        } catch (SerialPortException e) {
            logger.warn("Error receiving from serial port: ", e);
            return null;
        }
    }
}
