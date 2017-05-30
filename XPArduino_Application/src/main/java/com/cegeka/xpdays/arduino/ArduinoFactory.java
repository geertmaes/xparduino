package com.cegeka.xpdays.arduino;

import jssc.SerialPort;
import jssc.SerialPortException;

public class ArduinoFactory {

    public static Arduino create(SerialPort port) {
        openSerialPort(port);
        return new Arduino(port);
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
