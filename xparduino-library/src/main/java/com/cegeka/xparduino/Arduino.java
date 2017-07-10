package com.cegeka.xparduino;

import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.impl.BaseLedCommand;
import com.cegeka.xparduino.command.impl.BlinkCommand;
import com.cegeka.xparduino.command.impl.TrackSwitchCommand;
import com.cegeka.xparduino.command.impl.TrainCommand;
import com.cegeka.xparduino.state.ArduinoState;
import com.cegeka.xparduino.state.component.ComponentState;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.cegeka.xparduino.component.ComponentType.*;

public class Arduino implements Closeable {

    private final ArduinoState state;
    private final Channel<Command> commandChannel;
    private final ScheduledExecutorService executorService;

    public Arduino(ArduinoState state, Channel<Command> commandChannel) {
        this.state = state;
        this.commandChannel = commandChannel;
        this.executorService = Executors.newScheduledThreadPool(100);
    }

    public BaseLedCommand baseLed(int pin) {
        state.validatePin(pin);
        state.validateComponentOnPin(pin, BASE_LED);
        return new BaseLedCommand(pin, commandChannel);
    }

    public BlinkCommand baseLedBlink(int pin) {
        state.validatePin(pin);
        state.validateComponentOnPin(pin, BASE_LED);
        return new BlinkCommand(pin, commandChannel, executorService);
    }

    public TrainCommand train(int pin) {
        state.validatePin(pin);
        state.validateComponentOnPin(pin, INFRARED_EMITTER);
        return new TrainCommand(pin, commandChannel, executorService);
    }

    public TrackSwitchCommand trackSwitch(int pin) {
        state.validatePin(pin);
        state.validateComponentOnPin(pin, TRACK_SWITCH);
        return new TrackSwitchCommand(pin, commandChannel);
    }

    public <T extends ComponentState> T getState(int pin, Class<T> stateClass) {
        return state.getState(pin, stateClass);
    }

    @Override
    public void close() throws IOException {
        this.executorService.shutdown();
    }
}
