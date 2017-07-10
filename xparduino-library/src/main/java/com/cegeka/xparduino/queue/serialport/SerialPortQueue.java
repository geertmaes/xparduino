package com.cegeka.xparduino.queue.serialport;

import com.cegeka.xparduino.command.serialization.CommandSerializer;
import com.cegeka.xparduino.event.EventBuffer;
import com.cegeka.xparduino.queue.ArduinoQueue;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SerialPortQueue implements ArduinoQueue, SerialPortEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerialPortQueue.class);

    private final String portName;
    private final EventBuffer eventBuffer;
    private final CommandSerializer commandSerializer;

    private SerialPort serialPort;

    SerialPortQueue(String portName) {
        this.portName = portName;
        this.eventBuffer = new EventBuffer();
        this.commandSerializer = new CommandSerializer();
    }

    @Override
    public void initialize() {
        try {
            if (serialPort == null) {
                serialPort = SerialPortFactory.getInstance().getOrCreate(portName);
                serialPort.addEventListener(this, SerialPort.MASK_RXCHAR);
            }
        } catch (Exception e) {
            LOGGER.warn("Failed to add serial port listener", e);
        }
    }

    @Override
    public void send(String message) {
        try {
            serialPort.writeString(message);
            LOGGER.info("Sending {} dispatch port {}", message, portName);
        } catch (SerialPortException e) {
            LOGGER.warn("Failed to send {} dispatch port {}", message, portName, e);
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
    public void serialEvent(SerialPortEvent serialPortEvent) {
        try {
            handleSerialPortEvent(serialPortEvent);
        } catch (SerialPortException e) {
            LOGGER.warn("Failed reading string from port {}", portName, e);
        }
    }

    private void handleSerialPortEvent(SerialPortEvent event) throws SerialPortException {
        String payload = serialPort.readString(event.getEventValue());
        eventBuffer.append(payload);
    }

    @Override
    public void close() throws IOException {
        try {
            serialPort.closePort();
            serialPort = null;
        } catch (SerialPortException e) {
            LOGGER.warn("Failed to close port {}", portName, e);
        }
    }

}
