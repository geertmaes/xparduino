package com.cegeka.xpdays.arduino.command.impl;

import com.cegeka.xpdays.arduino.command.RepeatingCommand;
import com.cegeka.xpdays.arduino.communication.CommandChannel;
import com.cegeka.xpdays.arduino.component.ComponentType;

import java.util.concurrent.ScheduledExecutorService;

import static com.cegeka.xpdays.arduino.component.ComponentType.INFRARED_EMITTER;

public class InfraredCommand extends RepeatingCommand<InfraredCommand>{

    private Color color;
    private int channel;
    private int speed;

    public InfraredCommand(int pin, CommandChannel commandChannel, ScheduledExecutorService scheduledExecutorService) {
        super(pin, commandChannel, scheduledExecutorService);
    }

    public InfraredCommand withColor(Color color) {
        this.color = color;
        return this;
    }

    public InfraredCommand withChannel(int channel) {
        this.channel = channel;
        return this;
    }

    public InfraredCommand withSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    @Override
    public String getAction() {
        return color.color+":"+channel+":"+speed;
    }

    @Override
    protected ComponentType getComponentType() {
        return INFRARED_EMITTER;
    }

    @Override
    public void onStop() {

    }

    public enum Color {
        RED(0), BLUE(1);

        int color;

        Color(int color){
            this.color = color;
        }

    }
}
