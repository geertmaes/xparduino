package com.cegeka.xparduino.command.impl;

import com.cegeka.xparduino.command.AbstractCommand;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.domain.Color;

import static java.lang.String.format;

public class TrainCommand extends AbstractCommand {

    private final int speed;
    private final int channel;
    private final Color color;

    public TrainCommand(int pin, int speed, int channel, Color color) {
        super(pin);
        this.speed = speed;
        this.channel = channel;
        this.color = color;
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
