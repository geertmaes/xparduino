package com.cegeka.xpdays.arduino;

import com.cegeka.xpdays.arduino.command.impl.BaseLEDCommand;
import com.cegeka.xpdays.arduino.command.impl.BlinkCommand;
import com.cegeka.xpdays.arduino.communication.CommandChannel;
import com.cegeka.xpdays.arduino.communication.CommandChannelImpl;
import com.cegeka.xpdays.arduino.component.ComponentType;
import jssc.SerialPort;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.lang.String.format;

public class Arduino implements Closeable {

    private final CommandChannel commandChannel;
    private final Map<Integer, ComponentType> components;
    private final ScheduledExecutorService executorService;

    Arduino(SerialPort serialPort, Map<Integer, ComponentType> components) {
        this.components = components;
        this.commandChannel = new CommandChannelImpl(serialPort);
        this.executorService = Executors.newScheduledThreadPool(100);
    }

    public BaseLEDCommand baseLed(int pin) {
        validatePin(pin, ComponentType.BASE_LED);
        return new BaseLEDCommand(pin, commandChannel);
    }

    public BlinkCommand baseLedBlink(int pin) {
        validatePin(pin, ComponentType.BASE_LED);
        return new BlinkCommand(pin, commandChannel, executorService);
    }

    @Override
    public void close() throws IOException {
        this.commandChannel.close();
    }

    private void validatePin(int pin, ComponentType actual) {
        ComponentType expected = components.get(pin);

        if (expected == null) {
            throw new ArduinoPinMisMatchException(format("No component configured on pin %d", pin));
        }
        if (expected != actual) {
            throw new ArduinoPinMisMatchException(format("Expected component %s on pin %d, but was %s", expected, pin, actual));
        }
    }
}
