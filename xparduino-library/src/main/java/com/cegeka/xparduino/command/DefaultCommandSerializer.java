package com.cegeka.xparduino.command;

import com.cegeka.xparduino.command.serialized.SerializedCommand;

public class DefaultCommandSerializer implements CommandDataSerializer<Command> {

    private final CommandCode code;

    public DefaultCommandSerializer(CommandCode code) {
        this.code = code;
    }

    @Override
    public SerializedCommand serialize(Command command) {
        return new SerializedCommand(code, command.getAction(), command.getComponent());
    }
}