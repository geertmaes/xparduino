package com.cegeka.xparduino.command.serialized;

import com.cegeka.xparduino.component.Component;

public class SerializedCommand {

    private final String action;
    private final Component component;

    public SerializedCommand(String action, Component component) {
        this.action = action;
        this.component = component;
    }

    public String action() {
        return action;
    }

    public int pin() {
        return component.getPin();
    }

    public Component component() {
        return component;
    }

    @Override
    public String toString() {
        return String.format("<%d:%d,%s>",
                component.getType().getValue(), component.getPin(), action);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SerializedCommand that = (SerializedCommand) o;

        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        return component != null ? component.equals(that.component) : that.component == null;
    }

    @Override
    public int hashCode() {
        int result = action != null ? action.hashCode() : 0;
        result = 31 * result + (component != null ? component.hashCode() : 0);
        return result;
    }

}
