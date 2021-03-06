package com.cegeka.xparduino;

import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.impl.baseled.BaseLedCommandBuilder;
import com.cegeka.xparduino.command.impl.trackswitch.TrackSwitchCommandBuilder;
import com.cegeka.xparduino.command.impl.train.TrainCommandBuilder;
import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.state.ArduinoState;
import com.cegeka.xparduino.state.component.ComponentState;

import static com.cegeka.xparduino.component.ComponentType.*;

public class Arduino {

    private final ArduinoState state;
    private final Channel<Command> commandChannel;

    public Arduino(ArduinoState state, Channel<Command> commandChannel) {
        this.state = state;
        this.commandChannel = commandChannel;
    }

    public BaseLedCommandBuilder baseLed(ComponentPin pin) {
        state.validatePin(pin);
        state.validateComponentOnPin(pin, BASE_LED);
        return new BaseLedCommandBuilder(pin, commandChannel);
    }

    public TrackSwitchCommandBuilder trackSwitch(ComponentPin pin) {
        state.validatePin(pin);
        state.validateComponentOnPin(pin, TRACK_SWITCH);
        return new TrackSwitchCommandBuilder(pin, commandChannel);
    }

    public TrainCommandBuilder train(ComponentPin pin) {
        state.validatePin(pin);
        state.validateComponentOnPin(pin, INFRARED_EMITTER);
        return new TrainCommandBuilder(pin, commandChannel);
    }

    public <T extends ComponentState> T getState(ComponentPin pin, Class<T> stateClass) {
        return state.getState(pin, stateClass);
    }
}
