package com.cegeka.xparduino.event.mapper.serializer;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventDataSerializer;
import com.cegeka.xparduino.event.mapper.deserializer.EventDeserializationException;
import com.cegeka.xparduino.event.serialized.SerializedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class EventSerializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventSerializer.class);

    private final Map<Class<? extends Event>, EventDataSerializer> serializerMap;

    EventSerializer(Map<Class<? extends Event>, EventDataSerializer> serializerMap) {
        this.serializerMap = serializerMap;
    }

    @SuppressWarnings("unchecked")
    public SerializedEvent serialize(Event event) {
        try {
            return serializerMap.get(event.getClass()).serialize(event);
        } catch (Exception e) {
            LOGGER.warn("Failed to serialize event ({})", event.getClass().getSimpleName());
            throw new EventDeserializationException(e);
        }
    }

}
