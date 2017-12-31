package com.cegeka.xparduino.command;

import com.cegeka.xparduino.command.serialized.SerializedCommand;

public class DefaultCommandSerializer<C extends Command> implements CommandDataSerializer<C> {

    private final CommandCode code;

    public DefaultCommandSerializer(CommandCode code) {
        this.code = code;
    }

    @Override
    public SerializedCommand serialize(C command) {
        return new SerializedCommand(code, command.getAction(), command.getComponent());
    }
}