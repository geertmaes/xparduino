package com.cegeka.xpdays.arduino;

import com.cegeka.xpdays.arduino.command.BaseLEDCommand;
import com.cegeka.xpdays.arduino.command.BlinkCommand;
import com.cegeka.xpdays.arduino.monitor.PhysicalSerialMonitor;
import com.cegeka.xpdays.arduino.monitor.SerialMonitor;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

public class Arduino implements Closeable {

    public static Arduino fromSerialPort(SerialPort port) {
        openSerialPort(port);
        return new Arduino(port);
    }

    public static List<SerialPort> scanAvailablePorts() {
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

    private final SerialMonitor monitor;
    private final ScheduledExecutorService executorService;

    private Arduino(SerialPort serialPort) {
        this.monitor = new PhysicalSerialMonitor(serialPort);
        this.executorService = Executors.newScheduledThreadPool(100);
    }

    public BaseLEDCommand baseLed() {
        return new BaseLEDCommand(monitor);
    }

    public BlinkCommand baseLedBlink() {
        return new BlinkCommand(monitor, executorService);
    }

    @Override
    public void close() throws IOException {
        this.monitor.close();
    }
}
