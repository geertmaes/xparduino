package com.cegeka.xpdays.arduino.command.impl;

import com.cegeka.xpdays.arduino.command.AbstractCommand;
import com.cegeka.xpdays.arduino.communication.CommandChannel;
import com.cegeka.xpdays.arduino.component.ComponentType;

import java.util.Arrays;

import static com.cegeka.xpdays.arduino.component.ComponentType.INFRARED_EMITTER;

public class InfraredCommand extends AbstractCommand{

    public enum Color {
        RED(0), BLUE(1);

        int color;

        Color(int color){
            this.color = color;
        }

        public static Color forColor(int color){
            return Arrays.stream(values())
                    .filter(c -> c.color == color)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }

    private Color color;
    private int channel;
    private int speed;

    public InfraredCommand(int pin, CommandChannel commandChannel) {
        super(pin, commandChannel);
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
        return color.color+","+channel+","+speed;
    }

    @Override
    protected ComponentType getComponentType() {
        return INFRARED_EMITTER;
    }
}
