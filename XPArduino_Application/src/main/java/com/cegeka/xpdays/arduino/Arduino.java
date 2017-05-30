package com.cegeka.xpdays.arduino;

import com.cegeka.xpdays.arduino.command.BaseLEDCommand;
import com.cegeka.xpdays.arduino.command.BlinkCommand;
import com.cegeka.xpdays.arduino.communication.CommandChannel;
import com.cegeka.xpdays.arduino.communication.CommandChannelImpl;
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

    private final CommandChannel commandChannel;
    private final ScheduledExecutorService executorService;

    Arduino(SerialPort serialPort) {
        this.commandChannel = new CommandChannelImpl(serialPort);
        this.executorService = Executors.newScheduledThreadPool(100);
    }

    public BaseLEDCommand baseLed() {
        return new BaseLEDCommand(commandChannel);
    }

    public BlinkCommand baseLedBlink() {
        return new BlinkCommand(commandChannel, executorService);
    }

    @Override
    public void close() throws IOException {
        this.monitor.close();
    }
}
