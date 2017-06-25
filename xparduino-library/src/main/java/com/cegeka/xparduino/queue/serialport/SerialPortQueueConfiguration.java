package com.cegeka.xparduino.queue.serialport;

import com.cegeka.xparduino.configuration.ArduinoConfigurationException;
import com.cegeka.xparduino.queue.ArduinoQueue;
import com.cegeka.xparduino.queue.ArduinoQueueConfiguration;

import static java.lang.String.format;

public class SerialPortQueueConfiguration implements ArduinoQueueConfiguration {

    private static final String QUEUE_CREATION_FAILED = "Failed to create serial port queue (%s)";

    private final ArduinoQueue arduinoQueue;

    public SerialPortQueueConfiguration(String portName) {
        this.arduinoQueue = createSerialPortQueue(portName);
    }

    private ArduinoQueue createSerialPortQueue(String portName) {
        try {
            return SerialPortQueueFactory.getInstance().getOrCreate(portName);
        } catch (Exception e) {
            throw new ArduinoConfigurationException(format(QUEUE_CREATION_FAILED, portName), e);
        }
    }

    @Override
    public ArduinoQueue getQueue() {
        return arduinoQueue;
    }
}
