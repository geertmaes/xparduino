package com.cegeka.xparduino.command.mapper;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.mapper.deserializer.CommandDeserializer;
import com.cegeka.xparduino.command.mapper.deserializer.CommandDeserializerFactory;
import com.cegeka.xparduino.command.mapper.serializer.CommandSerializer;
import com.cegeka.xparduino.command.mapper.serializer.CommandSerializerFactory;

import java.util.Set;

public class CommandMapperFactory {

    private final CommandSerializerFactory commandSerializerFactory
            = new CommandSerializerFactory();
    private final CommandDeserializerFactory commandDeserializerFactory
            = new CommandDeserializerFactory();

    public CommandMapper create(Set<Class<? extends Command>> commands) {
        CommandSerializer serializer
                = commandSerializerFactory.create(commands);
        CommandDeserializer deserializer
                = commandDeserializerFactory.create(commands);

        return new CommandMapper(serializer, deserializer);
    }
}
