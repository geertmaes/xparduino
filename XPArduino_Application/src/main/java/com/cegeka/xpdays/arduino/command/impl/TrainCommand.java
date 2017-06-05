package com.cegeka.xpdays.arduino.command.impl;

import com.cegeka.xpdays.arduino.command.RepeatingCommand;
import com.cegeka.xpdays.arduino.communication.CommandChannel;
import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.model.Color;

import java.util.concurrent.ScheduledExecutorService;

import static com.cegeka.xpdays.arduino.component.ComponentType.INFRARED_EMITTER;
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
        return INFRARED_EMITTER;
    }
}
