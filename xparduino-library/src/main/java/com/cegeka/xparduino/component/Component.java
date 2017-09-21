package com.cegeka.xparduino.component;

public class Component {

    private final ComponentPin pin;
    private final ComponentType type;

    public Component(ComponentPin pin, ComponentType type) {
        this.pin = pin;
        this.type = type;
    }

    public ComponentPin getPin() {
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
        int result = pin != null ? pin.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

}
