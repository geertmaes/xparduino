package com.cegeka.xparduino.event.serialized.serializer;

import com.cegeka.xparduino.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

public class SerializedEventSerializerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerializedEventSerializerFactory.class);

    public SerializedEventSerializer create(Set<Class<? extends Event>> events) {
        Map<Class<? extends Event>, EventSerializer> serializerMap = events.stream()
                .collect(toMap(clazz -> clazz, this::createSerializer));

        return new SerializedEventSerializer(serializerMap);
    }

    private EventSerializer<? extends Event> createSerializer(Class<? extends Event> eventClass) {
        try {
            return extractEventSerializerClass(eventClass).newInstance();
        } catch (Exception e) {
            LOGGER.warn("Failed to create event serializer from event ({})", eventClass.getSimpleName());
            throw new SerializedEventSerializationException(e);
        }
    }

    private Class<? extends EventSerializer<? extends Event>> extractEventSerializerClass(Class<? extends Event> eventClass) {
        try {
            return eventClass.getAnnotation(EventMapping.class).serializer();
        } catch (Exception e) {
            LOGGER.warn("Failed to extract event serializer from event ({})", eventClass.getSimpleName());
            throw new SerializedEventSerializationException(e);
        }
    }
}
