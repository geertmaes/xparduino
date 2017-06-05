package com.cegeka.xpdays.arduino.command.impl;


import com.cegeka.xpdays.arduino.command.AbstractCommand;
import com.cegeka.xpdays.arduino.model.Direction;
import com.cegeka.xpdays.arduino.communication.CommandChannel;
import com.cegeka.xpdays.arduino.component.ComponentType;

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
