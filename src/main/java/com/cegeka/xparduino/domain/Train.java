package com.cegeka.xparduino.domain;

public class Train {

    private final String identifier;
    private final Color color;
    private final int channel;

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
