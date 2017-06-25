package com.cegeka.xparduino.command;

import com.cegeka.xparduino.component.ComponentSerializer;

import static java.lang.String.format;

public class CommandSerializer {

    private static final String COMMAND_FORMAT = "<%s,%s>";

    private final ComponentSerializer componentSerializer;

    public CommandSerializer() {
        this.componentSerializer = new ComponentSerializer();
    }

    public String serialize(Command command) {
        String action = command.getAction();
        String component = componentSerializer.serialize(command.getComponent());

        return format(COMMAND_FORMAT, component, action);
    }
}
