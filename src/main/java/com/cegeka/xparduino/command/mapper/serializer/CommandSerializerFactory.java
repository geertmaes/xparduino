package com.cegeka.xparduino.command.mapper.serializer;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.CommandDataSerializer;
import com.cegeka.xparduino.command.CommandMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

public class CommandSerializerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandSerializerFactory.class);

    public CommandSerializer create(Set<Class<? extends Command>> commands) {
        Map<Class<? extends Command>, CommandDataSerializer> serializerMap = commands.stream()
                .collect(toMap(clazz -> clazz, this::createSerializer));

        return new CommandSerializer(serializerMap);
    }

    private CommandDataSerializer<? extends Command> createSerializer(Class<? extends Command> commandClass) {
        try {
            return extractCommandSerializerClass(commandClass).newInstance();
        } catch (Exception e) {
            LOGGER.warn("Failed to create command serializer from command ({})", commandClass.getSimpleName());
            throw new CommandSerializationException(e);
        }
    }

    private Class<? extends CommandDataSerializer<? extends Command>> extractCommandSerializerClass(Class<? extends Command> commandClass) {
        try {
            return commandClass.getAnnotation(CommandMapping.class).serializer();
        } catch (Exception e) {
            LOGGER.warn("Failed to extract command serializer from command ({})", commandClass.getSimpleName());
            throw new CommandSerializationException(e);
        }
    }
}
