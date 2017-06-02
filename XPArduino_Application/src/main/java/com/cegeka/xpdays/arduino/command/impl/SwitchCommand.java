package com.cegeka.xpdays.arduino.command.impl;


import com.cegeka.xpdays.arduino.command.AbstractCommand;
import com.cegeka.xpdays.arduino.communication.CommandChannel;
import com.cegeka.xpdays.arduino.component.ComponentType;

public class SwitchCommand extends AbstractCommand {


    private boolean emitting;

    public SwitchCommand(int pin, CommandChannel commandChannel) {
        super(pin, commandChannel);
    }

    public SwitchCommand withEmitting(boolean emitting) {
        this.emitting = emitting;
        return this;
    }


    @Override
    public String getAction() {
        emitting = !emitting;
        return emitting ? "LEFT" : "RIGHT";
    }

    @Override
    protected ComponentType getComponentType() {
        return ComponentType.SWITCH;
    }

}
