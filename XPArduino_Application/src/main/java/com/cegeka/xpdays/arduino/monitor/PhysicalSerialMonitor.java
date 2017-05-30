package com.cegeka.xpdays.arduino.monitor;

import jssc.SerialPort;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.Consumer;

import static jssc.SerialPort.MASK_RXCHAR;

public class PhysicalSerialMonitor implements SerialMonitor {

    private final Logger logger = LoggerFactory.getLogger(PhysicalSerialMonitor.class);

    private final SerialPort port;
    private final SerialPortEventListenerImpl portEventListener;

    public PhysicalSerialMonitor(SerialPort port) {
        this.port = port;
        this.portEventListener = new SerialPortEventListenerImpl(port);
        setupEventListener(port);
    }

    private void setupEventListener(SerialPort port) {
        try {
            port.addEventListener(portEventListener, MASK_RXCHAR);
        } catch (SerialPortException e) {
            logger.warn("Failed to listen on port {}", port, e);
        }
    }

    public void send(String message) {
        try {
            port.writeString(message);
            logger.info("Sending {} on port {}", message, port);
        } catch (SerialPortException e) {
            logger.warn("Failed to send {} on port {}", message, port, e);
        }
    }

    @Override
    public void onMessage(Consumer<String> listener) {
        portEventListener.addListener(listener);
    }

    @Override
    public void close() throws IOException {
        try {
            port.closePort();
        } catch (SerialPortException e) {
            throw new IOException(e);
        }
    }
}
