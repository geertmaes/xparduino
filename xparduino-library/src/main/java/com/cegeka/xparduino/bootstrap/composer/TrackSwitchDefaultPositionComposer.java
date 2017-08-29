package com.cegeka.xparduino.bootstrap.composer;

import com.cegeka.xparduino.bootstrap.ArduinoBootstrap;
import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.impl.TrackSwitchCommand;
import com.cegeka.xparduino.domain.Direction;
import com.cegeka.xparduino.state.component.ComponentState;

import static com.cegeka.xparduino.component.ComponentType.TRACK_SWITCH;

public class TrackSwitchDefaultPositionComposer implements Composer {

    private static final Direction DEFAULT_DIRECTION = Direction.LEFT;

    @Override
    public void compose(ArduinoBootstrap bootstrap) {
        Channel<Command> commandChannel = bootstrap.getCommandChannel();

        bootstrap.getArduinoState()
                .getComponentStates(TRACK_SWITCH)
                .forEach(state -> moveToLeft(state, commandChannel));
    }

    private void moveToLeft(ComponentState state, Channel<Command> commandChannel) {
        new TrackSwitchCommand(state.getPin(), commandChannel)
                .withDirection(DEFAULT_DIRECTION)
                .execute();
    }

}
