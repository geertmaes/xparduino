package com.cegeka.xpdays.arduino.rest.domain;

import com.cegeka.xpdays.arduino.model.Color;

public class Train {
    private String identifier;
    private Color color;
    private int channel;

    public Train(String identifier, Color color, int channel) {
        this.identifier = identifier;
        this.color = color;
        this.channel = channel;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Color getColor() {
        return color;
    }

    public int getChannel() {
        return channel;
    }
}
