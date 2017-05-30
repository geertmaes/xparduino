package com.cegeka.xpdays.arduino.command;

import com.cegeka.xpdays.arduino.component.Component;
import com.cegeka.xpdays.arduino.monitor.SerialMonitor;

import java.util.concurrent.ScheduledExecutorService;

public class BlinkCommand extends RepeatingCommand {

    private boolean emitting = false;

    public BlinkCommand(SerialMonitor monitor, ScheduledExecutorService executorService) {
        super(monitor, executorService);
    }

    @Override
    public String getAction() {
        emitting = !emitting;
        return emitting ? "ON" : "OFF";
    }

    @Override
    public Component getComponent() {
        return Component.BASE_LED;
    }
}
