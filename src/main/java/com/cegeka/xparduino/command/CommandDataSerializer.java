package com.cegeka.xparduino.command;

import com.cegeka.xparduino.command.serialized.SerializedCommand;

@FunctionalInterface
public interface CommandDataSerializer<T extends Command> {

    SerializedCommand serialize(T command);
}
