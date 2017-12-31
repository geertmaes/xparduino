package com.cegeka.xparduino.command.serialized;

import com.cegeka.xparduino.command.CommandCode;
import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.mapper.ComponentMapper;

import java.util.regex.Pattern;

import static java.lang.String.format;

public class SerializedCommandFactory {

    private static final String COMMAND_PART_SEPARATOR = ",";
    private static final Pattern COMMAND_FORMAT = Pattern.compile("^<\\d*:\\d*,\\d*,.*>$");
    private static final String INVALID_FORMAT = "Command (%s) has invalid format";

    private final ComponentMapper componentMapper = new ComponentMapper();

    public SerializedCommand create(String command) {
        validateCommandFormat(command);

        String commandData = removeCommandIdentifiers(command);
        String[] commandParts = commandData.split(COMMAND_PART_SEPARATOR);

        return new SerializedCommand(
                extractCode(commandParts),
                extractAction(commandParts),
                extractComponent(commandParts));
    }

    private void validateCommandFormat(String payload) {
        if (!COMMAND_FORMAT.matcher(payload).matches()) {
            throw new SerializedCommandFormatException(format(INVALID_FORMAT, payload));
        }
    }

    private String removeCommandIdentifiers(String command) {
        return command.substring(1, command.length() - 1);
    }

    private CommandCode extractCode(String[] commandParts) {
        return CommandCode.valueOf(Integer.parseInt(commandParts[1]));
    }

    private String extractAction(String[] commandParts) {
        return commandParts[2];
    }

    private Component extractComponent(String[] commandParts) {
        return componentMapper.deserialize(commandParts[0]);
    }

}
