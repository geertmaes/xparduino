package com.cegeka.xpdays.arduino.command;

import com.cegeka.xpdays.arduino.communication.CommandChannel;
import com.cegeka.xpdays.arduino.component.Component;
import com.cegeka.xpdays.arduino.component.ComponentType;

@SuppressWarnings("unchecked")
public abstract class AbstractCommand implements Command {

    private final CommandChannel commandChannel;
    private final int pin;

    protected AbstractCommand(int pin, CommandChannel commandChannel) {
        this.pin = pin;
        this.commandChannel = commandChannel;
    }

    @Override
    public Component getComponent() {
        return new Component(pin, getComponentType());
    }

    @Override
    public abstract String getAction();

    protected abstract ComponentType getComponentType();

    @Override
    public void execute() {
        this.commandChannel.send(this);
    }
}
