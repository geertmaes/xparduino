package com.cegeka.xparduino.event.mapper.serializer;

import com.cegeka.xparduino.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

public class EventSerializerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventSerializerFactory.class);

    public EventSerializer create(Set<Class<? extends Event>> events) {
        Map<Class<? extends Event>, EventDataSerializer> serializerMap = events.stream()
                .collect(toMap(clazz -> clazz, this::createSerializer));

        return new EventSerializer(serializerMap);
    }

    private EventDataSerializer<? extends Event> createSerializer(Class<? extends Event> eventClass) {
        try {
            return extractEventSerializerClass(eventClass).newInstance();
        } catch (Exception e) {
            LOGGER.warn("Failed to create event serializer from event ({})", eventClass.getSimpleName());
            throw new EventSerializationException(e);
        }
    }

    private Class<? extends EventDataSerializer<? extends Event>> extractEventSerializerClass(Class<? extends Event> eventClass) {
        try {
            return eventClass.getAnnotation(EventMapping.class).serializer();
        } catch (Exception e) {
            LOGGER.warn("Failed to extract event serializer from event ({})", eventClass.getSimpleName());
            throw new EventSerializationException(e);
        }
    }
}
