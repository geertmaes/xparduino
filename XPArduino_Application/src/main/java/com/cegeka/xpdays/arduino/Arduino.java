package com.cegeka.xpdays.arduino;

import com.cegeka.xpdays.arduino.command.impl.BaseLEDCommand;
import com.cegeka.xpdays.arduino.command.impl.BlinkCommand;
import com.cegeka.xpdays.arduino.command.impl.TrackSwitchCommand;
import com.cegeka.xpdays.arduino.command.impl.TrainCommand;
import com.cegeka.xpdays.arduino.channel.CommandChannel;
import com.cegeka.xpdays.arduino.channel.EventChannel;
import com.cegeka.xpdays.arduino.channel.serialport.SerialPortCommandChannel;
import com.cegeka.xpdays.arduino.channel.serialport.SerialPortEventChannel;
import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.state.ArduinoState;
import com.cegeka.xpdays.arduino.state.ComponentState;
import jssc.SerialPort;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.cegeka.xpdays.arduino.component.ComponentType.*;

public class Arduino implements Closeable {

    private final ArduinoState arduinoState;
    private final EventChannel eventChannel;
    private final CommandChannel commandChannel;
    private final ScheduledExecutorService executorService;

    Arduino(SerialPort serialPort, Map<Integer, ComponentType> components) {
        this.arduinoState = new ArduinoState(components);
        this.eventChannel = new SerialPortEventChannel(serialPort);
        this.commandChannel = new SerialPortCommandChannel(serialPort);
        this.executorService = Executors.newScheduledThreadPool(100);
        registerStateEventDispatcher();
    }

    private void registerStateEventDispatcher() {
        eventChannel.registerListener(arduinoState.getStateEventDispatcher());
    }

    public BaseLEDCommand baseLed(int pin) {
        arduinoState.validatePinConfigured(pin);
        arduinoState.validatePinComponent(pin, BASE_LED);
        return new BaseLEDCommand(pin, commandChannel);
    }

    public BlinkCommand baseLedBlink(int pin) {
        arduinoState.validatePinConfigured(pin);
        arduinoState.validatePinComponent(pin, BASE_LED);
        return new BlinkCommand(pin, commandChannel, executorService);
    }

    public TrainCommand train(int pin) {
        arduinoState.validatePinConfigured(pin);
        arduinoState.validatePinComponent(pin, INFRARED_EMITTER);
        return new TrainCommand(pin, commandChannel, executorService);
    }

    public TrackSwitchCommand trackSwitch(int pin) {
        arduinoState.validatePinConfigured(pin);
        arduinoState.validatePinComponent(pin, TRACK_SWITCH);
        return new TrackSwitchCommand(pin, commandChannel);
    }

    public <T extends ComponentState> T getState(int pin, Class<T> stateClass) {
        return arduinoState.getState(pin, stateClass);
    }

    @Override
    public void close() throws IOException {
        this.commandChannel.close();
    }
}
