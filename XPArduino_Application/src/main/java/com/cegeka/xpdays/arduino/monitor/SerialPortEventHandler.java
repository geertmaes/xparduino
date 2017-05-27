package com.cegeka.xpdays.arduino.monitor;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerialPortEventHandler implements SerialPortEventListener {

    private final Logger logger = LoggerFactory.getLogger(SerialPortEventHandler.class);

    private final SerialPort port;
    private final MessageListener listener;

    public SerialPortEventHandler(SerialPort port, MessageListener listener) {
        this.port = port;
        this.listener = listener;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (isValidEvent(event)) {
            String text = readFromPort(event);
            listener.onMessage(text);
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
