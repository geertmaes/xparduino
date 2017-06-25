package com.cegeka.xparduino.command.impl;

import com.cegeka.xparduino.command.AbstractCommand;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.channel.CommandChannel;

public class BaseLEDCommand extends AbstractCommand {

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
