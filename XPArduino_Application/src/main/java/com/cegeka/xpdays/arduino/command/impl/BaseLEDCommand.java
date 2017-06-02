package com.cegeka.xpdays.arduino.command.impl;

import com.cegeka.xpdays.arduino.command.AbstractCommand;
import com.cegeka.xpdays.arduino.communication.CommandChannel;
import com.cegeka.xpdays.arduino.component.ComponentType;

public class BaseLEDCommand extends AbstractCommand{

    private boolean emitting;

    public BaseLEDCommand(int pin, CommandChannel commandChannel) {
        super(pin, commandChannel);
    }

    public BaseLEDCommand withEmitting(boolean emitting) {
        this.emitting = emitting;
        return this;
    }

    @Override
    public String getAction() {
        return emitting ? "ON" : "OFF";
    }

    @Override
    protected ComponentType getComponentType() {
        return ComponentType.BASE_LED;
    }
}
