package com.cegeka.xpdays.arduino;

import com.cegeka.xpdays.arduino.command.impl.BaseLEDCommand;
import com.cegeka.xpdays.arduino.command.impl.BlinkCommand;
import com.cegeka.xpdays.arduino.command.impl.InfraredCommand;
import com.cegeka.xpdays.arduino.communication.CommandChannel;
import com.cegeka.xpdays.arduino.communication.CommandChannelImpl;
import com.cegeka.xpdays.arduino.communication.EventChannel;
import com.cegeka.xpdays.arduino.communication.EventChannelImpl;
import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.dispatch.EventListener;
import com.cegeka.xpdays.arduino.listener.DynamicEventListener;
import com.cegeka.xpdays.arduino.state.ComponentState;
import jssc.SerialPort;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;

public class Arduino implements Closeable {

    private final ArduinoState arduinoState;
    private final EventChannel eventChannel;
    private final CommandChannel commandChannel;
    private final ScheduledExecutorService executorService;

    Arduino(SerialPort serialPort, Map<Integer, ComponentType> components) {
        this.arduinoState = new ArduinoState(components);
        this.eventChannel = new EventChannelImpl(serialPort);
        this.commandChannel = new CommandChannelImpl(serialPort);
        this.executorService = Executors.newScheduledThreadPool(100);

        registerStateEventListeners();
    }

    private void registerStateEventListeners() {
        this.arduinoState.getComponentStates()
                .forEach(eventChannel::registerEventListener);
    }

    public void registerEventListener(EventListener listener) {
        eventChannel.registerEventListener(listener);
    }

    public <T extends Event> void registerDynamicListener(Consumer<T> listener, Class<T> eventClass) {
        eventChannel.registerEventListener(new DynamicEventListener<>(listener, eventClass));
    }

    public BaseLEDCommand baseLed(int pin) {
        arduinoState.validatePinConfigured(pin);
        arduinoState.validatePinComponent(pin, ComponentType.BASE_LED);
        return new BaseLEDCommand(pin, commandChannel);
    }

    public BlinkCommand baseLedBlink(int pin) {
        arduinoState.validatePinConfigured(pin);
        arduinoState.validatePinComponent(pin, ComponentType.BASE_LED);
        return new BlinkCommand(pin, commandChannel, executorService);
    }

    public InfraredCommand infrared(int pin){
        arduinoState.validatePinConfigured(pin);
        arduinoState.validatePinComponent(pin, ComponentType.INFRARED_EMITTER);
        return new InfraredCommand(pin, commandChannel);
    }

    public <T extends ComponentState> T getState(int pin, Class<T> stateClass) {
        return arduinoState.getState(pin, stateClass);
    }

    @Override
    public void close() throws IOException {
        this.commandChannel.close();
    }
}
