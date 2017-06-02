package com.cegeka.xpdays.arduino.command.impl;


import com.cegeka.xpdays.arduino.command.AbstractCommand;
import com.cegeka.xpdays.arduino.common.Direction;
import com.cegeka.xpdays.arduino.communication.CommandChannel;
import com.cegeka.xpdays.arduino.component.ComponentType;

public class SwitchCommand extends AbstractCommand {

    private Direction direction;

    public SwitchCommand(int pin, CommandChannel commandChannel) {
        super(pin, commandChannel);
    }

    public SwitchCommand withDirection(Direction direction) {
        this.direction = direction;
        return this;
    }


    @Override
    public String getAction() {
        return direction.name();
    }

    @Override
    protected ComponentType getComponentType() {
        return ComponentType.SWITCH;
    }

}
