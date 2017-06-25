package com.cegeka.xparduino.command;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.channel.CommandChannel;
import com.cegeka.xparduino.component.ComponentType;

public abstract class AbstractCommand implements Command {

    private final CommandChannel commandChannel;
    private final int pin;

    protected AbstractCommand(int pin, CommandChannel commandChannel) {
        this.pin = pin;
        this.commandChannel = commandChannel;
    }

    @Override
    public abstract String getAction();

    protected abstract ComponentType getComponentType();

    @Override
    public Component getComponent() {
        return new Component(pin, getComponentType());
    }

    @Override
    public void execute() {
        this.commandChannel.send(this);
    }
}
