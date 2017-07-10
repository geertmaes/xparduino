package com.cegeka.xparduino.event.serialized.deserializer;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventCode;
import com.cegeka.xparduino.event.EventDeserializer;
import com.cegeka.xparduino.event.EventMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

public class SerializedEventDeserializerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerializedEventDeserializerFactory.class);

    public SerializedEventDeserializer create(Set<Class<? extends Event>> events) {
        Map<EventCode, EventDeserializer<? extends Event>> deserializerMap = events.stream()
                .collect(toMap(this::extractEventCode, this::createDeserializer));

        return new SerializedEventDeserializer(deserializerMap);
    }

    private EventCode extractEventCode(Class<? extends Event> eventClass) {
        try {
            return eventClass.getAnnotation(EventMapping.class).code();
        } catch (Exception e) {
            LOGGER.warn("Failed to extract event code from event ({})", eventClass.getSimpleName());
            throw new SerializedEventDeserializationException(e);
        }
    }

    private EventDeserializer<? extends Event> createDeserializer(Class<? extends Event> eventClass) {
        try {
            return extractEventDeserializerClass(eventClass).newInstance();
        } catch (Exception e) {
            LOGGER.warn("Failed to create event deserializer from event ({})", eventClass.getSimpleName());
            throw new SerializedEventDeserializationException(e);
        }
    }

    private Class<? extends EventDeserializer<? extends Event>> extractEventDeserializerClass(Class<? extends Event> eventClass) {
        try {
            return eventClass.getAnnotation(EventMapping.class).deserializer();
        } catch (Exception e) {
            LOGGER.warn("Failed to extract event deserializer from event ({})", eventClass.getSimpleName());
            throw new SerializedEventDeserializationException(e);
        }
    }
}
