package com.cegeka.xparduino.command.mapper.deserializer;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.CommandCode;
import com.cegeka.xparduino.command.CommandDataDeserializer;
import com.cegeka.xparduino.command.CommandMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

public class CommandDeserializerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandDeserializerFactory.class);

    public CommandDeserializer create(Set<Class<? extends Command>> commands) {
        Map<CommandCode, CommandDataDeserializer<? extends Command>> deserializerMap = commands.stream()
                .collect(toMap(this::extractCommandCode, this::createDeserializer));

        return new CommandDeserializer(deserializerMap);
    }

    private CommandCode extractCommandCode(Class<? extends Command> commandClass) {
        try {
            return commandClass.getAnnotation(CommandMapping.class).code();
        } catch (Exception e) {
            LOGGER.warn("Failed to extract command code from command ({})", commandClass.getSimpleName());
            throw new CommandDeserializationException(e);
        }
    }

    private CommandDataDeserializer<? extends Command> createDeserializer(Class<? extends Command> commandClass) {
        try {
            return extractCommandDeserializerClass(commandClass).newInstance();
        } catch (Exception e) {
            LOGGER.warn("Failed to create event deserializer from event ({})", commandClass.getSimpleName());
            throw new CommandDeserializationException(e);
        }
    }

    private Class<? extends CommandDataDeserializer<? extends Command>> extractCommandDeserializerClass(Class<? extends Command> commandClass) {
        try {
            return commandClass.getAnnotation(CommandMapping.class).deserializer();
        } catch (Exception e) {
            LOGGER.warn("Failed to extract command deserializer from command ({})", commandClass.getSimpleName());
            throw new CommandDeserializationException(e);
        }
    }
}
