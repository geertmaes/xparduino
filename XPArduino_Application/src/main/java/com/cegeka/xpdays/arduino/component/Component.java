package com.cegeka.xpdays.arduino.component;

public class Component {

    private final int pin;
    private final ComponentType type;

    public Component(int pin, ComponentType type) {
        this.pin = pin;
        this.type = type;
    }

    public int getPin() {
        return pin;
    }

    public ComponentType getType() {
        return type;
    }
}
