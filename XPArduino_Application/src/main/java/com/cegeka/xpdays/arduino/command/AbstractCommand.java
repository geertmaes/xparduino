package com.cegeka.xpdays.arduino.command;

import com.cegeka.xpdays.arduino.component.Component;
import com.cegeka.xpdays.arduino.monitor.SerialMonitor;

import static java.lang.String.format;

public abstract class AbstractCommand implements Command {

    private static final String COMMAND_FORMAT = "<%s,%s>";

    private final SerialMonitor monitor;

    AbstractCommand(SerialMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public abstract String getAction();

    @Override
    public abstract Component getComponent();

    @Override
    public void execute() {
        this.monitor.send(format(COMMAND_FORMAT, getComponent().ordinal(), getAction()));
    }
}
