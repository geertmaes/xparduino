package com.cegeka.xpdays.arduino.rest.domain;

import com.cegeka.xpdays.arduino.command.impl.InfraredCommand;

public class Train {
    private String identifier;
    private InfraredCommand.Color color;
    private int channel;

    public Train(String identifier, InfraredCommand.Color color, int channel) {
        this.identifier = identifier;
        this.color = color;
        this.channel = channel;
    }

    public String getIdentifier() {
        return identifier;
    }

    public InfraredCommand.Color getColor() {
        return color;
    }

    public int getChannel() {
        return channel;
    }
}
