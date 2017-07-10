package com.cegeka.xparduino.command.serialization;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.component.serialization.ComponentSerializer;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class CommandSerializer {

    private static final String COMMAND_FORMAT = "<%s,%s>";

    private final ComponentSerializer componentSerializer;

    public CommandSerializer() {
        this.componentSerializer = new ComponentSerializer();
    }

    public String serialize(Command command) {
        requireNonNull(command);

        String action = command.getAction();
        String component = componentSerializer.serialize(command.getComponent());

        return format(COMMAND_FORMAT, component, action);
    }
}
