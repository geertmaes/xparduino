package com.cegeka.xparduino.command.impl;


import com.cegeka.xparduino.command.AbstractCommand;
import com.cegeka.xparduino.channel.CommandChannel;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.domain.Direction;

public class TrackSwitchCommand extends AbstractCommand {

    private Direction direction;

    public TrackSwitchCommand(int pin, CommandChannel commandChannel) {
        super(pin, commandChannel);
    }

    public TrackSwitchCommand withDirection(Direction direction) {
        this.direction = direction;
        return this;
    }

    @Override
    public String getAction() {
        return direction.name();
    }

    @Override
    protected ComponentType getComponentType() {
        return ComponentType.TRACK_SWITCH;
    }
}
