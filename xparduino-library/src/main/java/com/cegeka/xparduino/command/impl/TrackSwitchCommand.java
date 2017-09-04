package com.cegeka.xparduino.command.impl;


import com.cegeka.xparduino.command.AbstractCommand;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.domain.Direction;

public class TrackSwitchCommand extends AbstractCommand {

    private final Direction direction;

    public TrackSwitchCommand(int pin, Direction direction) {
        super(pin);
        this.direction = direction;
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
