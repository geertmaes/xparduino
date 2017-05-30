package com.cegeka.xpdays.arduino.command;

import com.cegeka.xpdays.arduino.communication.CommandChannel;
import com.cegeka.xpdays.arduino.component.Component;

public abstract class AbstractCommand implements Command {

    private final CommandChannel commandChannel;

    AbstractCommand(CommandChannel commandChannel) {
        this.commandChannel = commandChannel;
    }

    @Override
    public abstract String getAction();

    @Override
    public abstract Component getComponent();

    @Override
    public void execute() {
        this.commandChannel.send(this);
    }
}
