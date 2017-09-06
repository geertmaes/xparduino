package com.cegeka.xparduino.command.serialized;

import com.cegeka.xparduino.command.CommandCode;
import com.cegeka.xparduino.component.Component;

public class SerializedCommand {

    private final CommandCode commandCode;
    private final String action;
    private final Component component;

    public SerializedCommand(CommandCode commandCode, String action, Component component) {
        this.commandCode = commandCode;
        this.action = action;
        this.component = component;
    }

    public CommandCode commandCode() {
        return commandCode;
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
        return String.format("<%d:%d,%d,%s>",
                component.getType().getValue(), component.getPin(), commandCode.value(), action);
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
