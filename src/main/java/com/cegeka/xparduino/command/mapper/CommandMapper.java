package com.cegeka.xparduino.command.mapper;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.mapper.deserializer.CommandDeserializer;
import com.cegeka.xparduino.command.mapper.serializer.CommandSerializer;
import com.cegeka.xparduino.command.serialized.SerializedCommand;
import com.cegeka.xparduino.command.serialized.SerializedCommandFactory;

public class CommandMapper {

    private final CommandSerializer commandSerializer;
    private final CommandDeserializer commandDeserializer;
    private final SerializedCommandFactory serializedCommandFactory;

    CommandMapper(CommandSerializer commandSerializer,
                  CommandDeserializer commandDeserializer) {
        this.commandSerializer = commandSerializer;
        this.commandDeserializer = commandDeserializer;
        this.serializedCommandFactory = new SerializedCommandFactory();
    }

    public Command toCommand(String command) {
        return commandDeserializer.deserialize(toSerializedCommand(command));
    }

    public Command toCommand(SerializedCommand command) {
        return commandDeserializer.deserialize(command);
    }

    public SerializedCommand toSerializedCommand(String command) {
        return serializedCommandFactory.create(command);
    }

    public SerializedCommand toSerializedCommand(Command command) {
        return commandSerializer.serialize(command);
    }

}
