package com.cegeka.xparduino.command.mapper.serializer;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.CommandDataSerializer;
import com.cegeka.xparduino.command.serialized.SerializedCommand;
import com.cegeka.xparduino.event.mapper.deserializer.EventDeserializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CommandSerializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandSerializer.class);

    private final Map<Class<? extends Command>, CommandDataSerializer> serializerMap;

    CommandSerializer(Map<Class<? extends Command>, CommandDataSerializer> serializerMap) {
        this.serializerMap = serializerMap;
    }

    @SuppressWarnings("unchecked")
    public SerializedCommand serialize(Command command) {
        try {
            return serializerMap.get(command.getClass()).serialize(command);
        } catch (Exception e) {
            LOGGER.warn("Failed to serialize command ({})", command.getClass().getSimpleName());
            throw new EventDeserializationException(e);
        }
    }

}
