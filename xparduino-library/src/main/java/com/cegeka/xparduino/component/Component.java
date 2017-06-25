package com.cegeka.xparduino.component;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Component component = (Component) o;

        if (pin != component.pin) return false;
        return type == component.type;
    }

    @Override
    public int hashCode() {
        int result = pin;
        result = 31 * result + type.hashCode();
        return result;
    }
}
