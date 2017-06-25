package com.cegeka.xparduino;

import com.cegeka.xparduino.channel.CommandChannel;
import com.cegeka.xparduino.channel.EventChannel;
import com.cegeka.xparduino.command.impl.BaseLEDCommand;
import com.cegeka.xparduino.command.impl.BlinkCommand;
import com.cegeka.xparduino.command.impl.TrackSwitchCommand;
import com.cegeka.xparduino.command.impl.TrainCommand;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.configuration.ArduinoConfiguration;
import com.cegeka.xparduino.state.ArduinoState;
import com.cegeka.xparduino.state.component.ComponentState;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Arduino implements Closeable {

    private static final int THREAD_POOL_SIZE = 100;

    public static Arduino create(ArduinoConfiguration configuration) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);
        ArduinoState state = new ArduinoState(configuration.getComponents());
        EventChannel eventChannel = new EventChannel(configuration.getQueue());
        CommandChannel commandChannel = new CommandChannel(configuration.getQueue());

        eventChannel.registerEventListener(state);
        executorService.submit(eventChannel::listenOnQueueChanges);
        return new Arduino(state, eventChannel, commandChannel, executorService);
    }

    private final ArduinoState state;
    private final EventChannel eventChannel;
    private final CommandChannel commandChannel;
    private final ScheduledExecutorService executorService;

    private Arduino(ArduinoState state,
                    EventChannel eventChannel,
                    CommandChannel commandChannel,
                    ScheduledExecutorService executorService) {
        this.state = state;
        this.eventChannel = eventChannel;
        this.commandChannel = commandChannel;
        this.executorService = executorService;
    }

    public BaseLEDCommand baseLed(int pin) {
        state.validatePin(pin);
        state.validatePinComponent(pin, ComponentType.BASE_LED);
        return new BaseLEDCommand(pin, commandChannel);
    }

    public BlinkCommand baseLedBlink(int pin) {
        state.validatePin(pin);
        state.validatePinComponent(pin, ComponentType.BASE_LED);
        return new BlinkCommand(pin, commandChannel, executorService);
    }

    public TrainCommand train(int pin) {
        state.validatePin(pin);
        state.validatePinComponent(pin, ComponentType.INFRARED_EMITTER);
        return new TrainCommand(pin, commandChannel, executorService);
    }

    public TrackSwitchCommand trackSwitch(int pin) {
        state.validatePin(pin);
        state.validatePinComponent(pin, ComponentType.TRACK_SWITCH);
        return new TrackSwitchCommand(pin, commandChannel);
    }

    public <T extends ComponentState> T getState(int pin, Class<T> stateClass) {
        return state.getState(pin, stateClass);
    }

    @Override
    public void close() throws IOException {
        this.eventChannel.close();
        this.commandChannel.close();
        this.executorService.shutdown();
    }
}
