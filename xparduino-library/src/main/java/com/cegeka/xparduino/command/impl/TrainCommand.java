package com.cegeka.xparduino.command.impl;

import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.domain.Color;
import com.cegeka.xparduino.channel.CommandChannel;
import com.cegeka.xparduino.command.RepeatingCommand;

import java.util.concurrent.ScheduledExecutorService;

import static java.lang.String.format;

public class TrainCommand extends RepeatingCommand<TrainCommand> {

    private int speed;
    private int channel;
    private Color color;

    public TrainCommand(int pin,
                        CommandChannel commandChannel,
                        ScheduledExecutorService scheduledExecutorService) {
        super(pin, commandChannel, scheduledExecutorService);
    }

    public TrainCommand withSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public TrainCommand withChannel(int channel) {
        this.channel = channel;
        return this;
    }

    public TrainCommand withColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public String getAction() {
        return format("%d:%d:%d", color.getColor(), channel, speed);
    }

    @Override
    protected ComponentType getComponentType() {
        return ComponentType.INFRARED_EMITTER;
    }
}
