package com.cegeka.xpdays.arduino.communication.serialport;

import com.cegeka.xpdays.arduino.communication.EventChannel;
import com.cegeka.xpdays.arduino.event.EventListener;
import com.cegeka.xpdays.arduino.event.dispatch.EventDispatcher;
import com.cegeka.xpdays.arduino.event.dispatch.EventDispatchingException;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SerialPortEventChannel implements EventChannel {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerialPortEventChannel.class);
    private static final String SCAN_PACKAGE = "com.cegeka.xpdays.arduino";

    private final SerialPort port;
    private final EventDispatcher eventDispatcher;

    public SerialPortEventChannel(SerialPort port) {
        this.port = port;
        this.eventDispatcher = EventDispatcher.fromPackage(SCAN_PACKAGE);
        setupSerialPortListener();
    }

    private void setupSerialPortListener() {
        try {
            this.port.addEventListener(new SerialPortEventChannelListener(port, eventDispatcher), SerialPort.MASK_RXCHAR);
        } catch (Exception e) {
            LOGGER.warn("Failed to add serial port listener", e);
        }
    }

    @Override
    public void send(String payload) {
        try {
            this.eventDispatcher.dispatch(payload);
        } catch (EventDispatchingException e) {
            LOGGER.warn("Failed dispatching event", e);
        }
    }

    @Override
    public void registerListener(EventListener listener) {
        this.eventDispatcher.registerListener(listener);
    }

    @Override
    public void close() throws IOException {
        try {
            port.closePort();
        } catch (SerialPortException e) {
            LOGGER.warn("Failed to close port {}", port, e);
        }
    }
}
