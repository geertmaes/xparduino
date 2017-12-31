package com.cegeka.xparduino.command;

import com.cegeka.xparduino.command.serialized.SerializedCommand;

@FunctionalInterface
public interface CommandDataDeserializer<T extends Command> {

    T deserialize(SerializedCommand command);
}
