package com.cegeka.xpdays.arduino;

import com.cegeka.xpdays.arduino.configuration.ArduinoConfiguration;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArduinoFactory {

    private static final String PORT_NOT_FOUND = "Configuration port not found";

    public static Arduino create(ArduinoConfiguration configuration) {
        SerialPort port = findSerialPort(configuration);
        openSerialPort(port);
        return new Arduino(port, configuration.getComponents());
    }

    private static SerialPort findSerialPort(ArduinoConfiguration configuration) {
        return getAvailablePorts().stream()
                .filter(serialPort -> isValidPortName(configuration, serialPort))
                .findFirst()
                .orElseThrow(() -> new ArduinoInitializationFailedException(PORT_NOT_FOUND));
    }

    private static boolean isValidPortName(ArduinoConfiguration configuration, SerialPort serialPort) {
        return serialPort.getPortName().equalsIgnoreCase(configuration.getPortName());
    }

    private static List<SerialPort> getAvailablePorts() {
        return Arrays.stream(SerialPortList.getPortNames())
                .map(SerialPort::new)
                .collect(Collectors.toList());
    }

    private static void openSerialPort(SerialPort port) {
        try {
            port.openPort();
            port.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            port.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
        } catch (SerialPortException e) {
            throw new ArduinoInitializationFailedException(e);
        }
    }

}
