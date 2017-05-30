package com.cegeka.xpdays.arduino.command;

import com.cegeka.xpdays.arduino.component.ComponentSerializer;

public class CommandSerializer {

    private final ComponentSerializer componentSerializer;

    public CommandSerializer() {
        this.componentSerializer = new ComponentSerializer();
    }

    public String serialize(Command command) {
        String action = command.getAction();
        String component = componentSerializer.serialize(command.getComponent());

        return String.format("<%s,%s>", component, action);
    }
}
