package com.cegeka.xparduino.command.impl;

import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.command.AbstractCommandBuilder;
import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.domain.Direction;

import java.util.concurrent.ScheduledExecutorService;

public class TrackSwitchCommandBuilder extends AbstractCommandBuilder<TrackSwitchCommand> {

    private Direction direction;

    public TrackSwitchCommandBuilder(int pin,
                                     Channel<Command> commandChannel,
                                     ScheduledExecutorService executorService) {
        super(pin, commandChannel, executorService);
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
        return new TrackSwitchCommand(pin, direction);
    }
}
