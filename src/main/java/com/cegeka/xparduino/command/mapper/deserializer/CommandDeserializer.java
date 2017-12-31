package com.cegeka.xparduino.command.mapper.deserializer;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.CommandCode;
import com.cegeka.xparduino.command.CommandDataDeserializer;
import com.cegeka.xparduino.command.serialized.SerializedCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CommandDeserializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandDeserializer.class);

    private final Map<CommandCode, CommandDataDeserializer<? extends Command>> deserializerMap;

    CommandDeserializer(Map<CommandCode, CommandDataDeserializer<? extends Command>> deserializerMap) {
        this.deserializerMap = deserializerMap;
    }

    public Command deserialize(SerializedCommand command) {
        try {
            return deserializerMap.get(command.commandCode()).deserialize(command);
        } catch (Exception e) {
            LOGGER.warn("Failed to deserialize command ({})", command);
            throw new CommandDeserializationException(e);
        }
    }
}
