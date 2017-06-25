package com.cegeka.xparduino.command.impl;

import com.cegeka.xparduino.command.RepeatingCommand;
import com.cegeka.xparduino.channel.CommandChannel;
import com.cegeka.xparduino.component.ComponentType;

import java.util.concurrent.ScheduledExecutorService;

public class BlinkCommand extends RepeatingCommand<BlinkCommand> {

    private boolean emitting = false;

    public BlinkCommand(int pin, CommandChannel commandChannel,
                        ScheduledExecutorService executorService) {
        super(pin, commandChannel, executorService);
    }

    @Override
    public String getAction() {
        emitting = !emitting;
        return emitting ? "ON" : "OFF";
    }

    @Override
    protected ComponentType getComponentType() {
        return ComponentType.BASE_LED;
    }
}
