package com.cegeka.xparduino.command.impl;

import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.command.AbstractCommand;
import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.component.ComponentType;

public class BaseLedCommand extends AbstractCommand {

    private boolean emitting;

    public BaseLedCommand(int pin, Channel<Command> commandChannel) {
        super(pin, commandChannel);
    }

    public BaseLedCommand withEmitting(boolean emitting) {
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
