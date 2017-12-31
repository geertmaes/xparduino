package com.cegeka.xparduino.command.impl.trackswitch;

import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.command.AbstractCommandBuilder;
import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.domain.Direction;

public class TrackSwitchCommandBuilder extends AbstractCommandBuilder<TrackSwitchCommand> {

    private Direction direction;

    public TrackSwitchCommandBuilder(ComponentPin pin,
                                     Channel<Command> commandChannel) {
        super(pin, commandChannel);
    }

    public TrackSwitchCommandBuilder left() {
        this.direction = Direction.LEFT;
        return this;
    }

    public TrackSwitchCommandBuilder right() {
        this.direction = Direction.RIGHT;
        return this;
    }

    public TrackSwitchCommandBuilder withDirection(Direction direction) {
        this.direction = direction;
        return this;
    }

    @Override
    public TrackSwitchCommand build() {
        return new TrackSwitchCommand(pin(), direction);
    }
}
