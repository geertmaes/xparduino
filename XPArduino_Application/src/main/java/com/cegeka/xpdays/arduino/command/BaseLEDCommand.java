package com.cegeka.xpdays.arduino.command;

import com.cegeka.xpdays.arduino.component.Component;
import com.cegeka.xpdays.arduino.monitor.SerialMonitor;

public class BaseLEDCommand extends AbstractCommand {

    private boolean emitting;

    public BaseLEDCommand(SerialMonitor monitor) {
        super(monitor);
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
    public Component getComponent() {
        return Component.BASE_LED;
    }
}
