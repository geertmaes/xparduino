package com.cegeka.xparduino.event.mapper.deserializer;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventCode;
import com.cegeka.xparduino.event.EventDataDeserializer;
import com.cegeka.xparduino.event.EventMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

public class EventDeserializerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventDeserializerFactory.class);

    public EventDeserializer create(Set<Class<? extends Event>> events) {
        Map<EventCode, EventDataDeserializer<? extends Event>> deserializerMap = events.stream()
                .collect(toMap(this::extractEventCode, this::createDeserializer));

        return new EventDeserializer(deserializerMap);
    }

    private EventCode extractEventCode(Class<? extends Event> eventClass) {
        try {
            return eventClass.getAnnotation(EventMapping.class).code();
        } catch (Exception e) {
            LOGGER.warn("Failed to extract event code from event ({})", eventClass.getSimpleName());
            throw new EventDeserializationException(e);
        }
    }

    private EventDataDeserializer<? extends Event> createDeserializer(Class<? extends Event> eventClass) {
        try {
            return extractEventDeserializerClass(eventClass).newInstance();
        } catch (Exception e) {
            LOGGER.warn("Failed to create event deserializer from event ({})", eventClass.getSimpleName());
            throw new EventDeserializationException(e);
        }
    }

    private Class<? extends EventDataDeserializer<? extends Event>> extractEventDeserializerClass(Class<? extends Event> eventClass) {
        try {
            return eventClass.getAnnotation(EventMapping.class).deserializer();
        } catch (Exception e) {
            LOGGER.warn("Failed to extract event deserializer from event ({})", eventClass.getSimpleName());
            throw new EventDeserializationException(e);
        }
    }
}
