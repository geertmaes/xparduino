package com.cegeka.xpdays.arduino.command;

import com.cegeka.xpdays.arduino.communication.CommandChannel;
import com.cegeka.xpdays.arduino.component.Component;

import java.util.concurrent.ScheduledExecutorService;

public class BlinkCommand extends RepeatingCommand<BlinkCommand> {

    private boolean emitting = false;

    public BlinkCommand(CommandChannel commandChannel, ScheduledExecutorService executorService) {
        super(commandChannel, executorService);
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
