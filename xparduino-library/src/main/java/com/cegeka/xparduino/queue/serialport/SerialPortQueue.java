package com.cegeka.xparduino.queue.serialport;

import com.cegeka.xparduino.event.EventBuffer;
import com.cegeka.xparduino.event.dispatch.EventDispatchingException;
import com.cegeka.xparduino.queue.ArduinoQueue;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SerialPortQueue
        implements ArduinoQueue, SerialPortEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerialPortQueue.class);

    private final SerialPort port;
    private final EventBuffer eventBuffer;

    SerialPortQueue(SerialPort port) {
        this.port = port;
        this.eventBuffer = new EventBuffer();
        listenOnSerialPortChanges();
    }

    private void listenOnSerialPortChanges() {
        try {
            this.port.addEventListener(this, SerialPort.MASK_RXCHAR);
        } catch (Exception e) {
            LOGGER.warn("Failed to add serial port listener", e);
        }
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        try {
            handleSerialPortEvent(serialPortEvent);
        } catch (SerialPortException e) {
            LOGGER.warn("Failed reading string from serial port", e);
        } catch (EventDispatchingException e) {
            LOGGER.warn("Failed dispatching event", e);
        }
    }

    @Override
    public void send(String message) {
        try {
            port.writeString(message);
            LOGGER.info("Sending {} dispatch port {}", message, port.getPortName());
        } catch (SerialPortException e) {
            LOGGER.warn("Failed to send {} dispatch port {}", message, port, e);
        }
    }

    @Override
    public String next() {
        return eventBuffer.next();
    }

    @Override
    public boolean hasNext() {
        return eventBuffer.hasNext();
    }

    @Override
    public void close() throws IOException {
        try {
            port.closePort();
        } catch (SerialPortException e) {
            LOGGER.warn("Failed to close port {}", port, e);
        }
    }

    private void handleSerialPortEvent(SerialPortEvent serialPortEvent) throws SerialPortException, EventDispatchingException {
        String payload = readString(serialPortEvent);
        eventBuffer.append(payload);
    }

    private String readString(SerialPortEvent event) throws SerialPortException {
        return this.port.readString(event.getEventValue());
    }
}
