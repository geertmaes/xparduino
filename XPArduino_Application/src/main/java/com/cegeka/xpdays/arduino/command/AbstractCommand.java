package com.cegeka.xpdays.arduino.command;

import com.cegeka.xpdays.arduino.communication.CommandChannel;
import com.cegeka.xpdays.arduino.component.Component;
import com.cegeka.xpdays.arduino.component.ComponentType;

@SuppressWarnings("unchecked")
public abstract class AbstractCommand<T extends AbstractCommand> implements Command {

    private final CommandChannel commandChannel;
    private int pin;

    protected AbstractCommand(CommandChannel commandChannel) {
        this.commandChannel = commandChannel;
    }

    public T withPin(int pin) {
        this.pin = pin;
        return (T) this;
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
