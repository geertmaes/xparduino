package com.cegeka.xparduino.command.serialization;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.component.mapper.ComponentMapper;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class CommandSerializer {

    private static final String COMMAND_FORMAT = "<%s,%s>";

    private final ComponentMapper componentMapper = new ComponentMapper();

    public String serialize(Command command) {
        requireNonNull(command);

        String action = command.getAction();
        String component = componentMapper.serialize(command.getComponent());

        return format(COMMAND_FORMAT, component, action);
    }
}
