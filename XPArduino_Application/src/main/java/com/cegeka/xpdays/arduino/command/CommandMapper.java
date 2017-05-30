package com.cegeka.xpdays.arduino.command;

public class CommandMapper {

    public String mapToString(Command command) {
        return String.format("<%s,%s>", command.getComponent().ordinal(), command.getAction());
    }
}
