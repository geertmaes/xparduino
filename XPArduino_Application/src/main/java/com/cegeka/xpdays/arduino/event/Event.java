package com.cegeka.xpdays.arduino.event;

public abstract class Event {

    private final int pin;

    protected Event(int pin) {
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }
}
