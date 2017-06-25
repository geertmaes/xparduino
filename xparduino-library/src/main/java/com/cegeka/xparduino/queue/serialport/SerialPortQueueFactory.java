package com.cegeka.xparduino.queue.serialport;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.util.Arrays;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static java.lang.String.format;

public class SerialPortQueueFactory {

    private static final SerialPortQueueFactory instance = new SerialPortQueueFactory();

    private static final String PORT_NOT_FOUND = "Failed to find serial port with name (%s)";
    private static final String PORT_OPEN_FAILED = "Failed to open serial port with name (%s)";

    public static SerialPortQueueFactory getInstance() {
        return instance;
    }

    private final Map<String, SerialPort> cache = newHashMap();

    private SerialPortQueueFactory() {}

    public SerialPortQueue getOrCreate(String portName) {
        return new SerialPortQueue(getSerialPort(portName));
    }

    private SerialPort getSerialPort(String portName) {
        return cache.containsKey(portName)
                ? cache.get(portName)
                : findAndOpenSerialPort(portName);
    }

    private SerialPort findAndOpenSerialPort(String portName) {
        SerialPort port = findSerialPort(portName);
        openSerialPort(port);
        cache.put(portName, port);
        return port;
    }

    private SerialPort findSerialPort(String portName) {
        return Arrays.stream(SerialPortList.getPortNames())
                .filter(name -> name.equalsIgnoreCase(portName))
                .findFirst()
                .map(SerialPort::new)
                .orElseThrow(() -> new SerialPortInitializationFailedException(format(PORT_NOT_FOUND, portName)));
    }

    private static void openSerialPort(SerialPort port) {
        try {
            if (!port.isOpened()) {
                port.openPort();
                port.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                port.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
            }
        } catch (SerialPortException e) {
            throw new SerialPortInitializationFailedException(format(PORT_OPEN_FAILED, port.getPortName()));
        }
    }
}
