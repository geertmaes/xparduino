package com.cegeka.xparduino.event.dispatch;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventCode;
import com.cegeka.xparduino.event.EventMapping;
import com.cegeka.xparduino.event.deserialize.EventDeserializer;
import com.cegeka.xparduino.event.deserialize.DefaultEventDeserializer;
import com.cegeka.xparduino.event.deserialize.EventDeserializerMapping;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

public class EventDispatcherState {

    static EventDispatcherState scanPackage(String scanPackage) {
        Reflections reflections = new Reflections(scanPackage);

        return new EventDispatcherState(
                reflections.getSubTypesOf(Event.class),
                reflections.getSubTypesOf(EventDeserializer.class));
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(EventDispatcherState.class);

    private final Map<EventCode, Class<? extends Event>> eventMap;
    private final Map<Class<? extends Event>, EventDeserializer> eventDeserializerMap;

    private EventDispatcherState(Set<Class<? extends Event>> eventClasses,
                                 Set<Class<? extends EventDeserializer>> eventDeserializerClasses) {
        this.eventMap = toEventsMap(eventClasses);
        this.eventDeserializerMap = toEventDeserializerMap(eventDeserializerClasses);
    }

    Optional<Class<? extends Event>> getEventClassByEventCode(EventCode eventCode) {
        return Optional.ofNullable(eventMap.get(eventCode));
    }

    EventDeserializer getEventDeserializerByEventClass(Class<? extends Event> eventClass) {
        return eventDeserializerMap
                .getOrDefault(eventClass, new DefaultEventDeserializer<>(eventClass));
    }

    private Map<EventCode, Class<? extends Event>> toEventsMap(Set<Class<? extends Event>> eventClasses) {
        return eventClasses.stream()
                .collect(toMap(this::extractEventCodeFromAnnotation, eventClass -> eventClass));
    }

    private EventCode extractEventCodeFromAnnotation(Class<? extends Event> eventClass) {
        try {
            return eventClass.getAnnotation(EventMapping.class).value();
        } catch (Exception e) {
            LOGGER.warn("Failed to extract event code for event class ({})", eventClass.getSimpleName());
            throw new EventDispatcherStateException(e);
        }
    }

    private Map<Class<? extends Event>, EventDeserializer> toEventDeserializerMap(Set<Class<? extends EventDeserializer>> eventDeserializerClasses) {
        return eventDeserializerClasses.stream()
                .filter(clazz -> !clazz.equals(DefaultEventDeserializer.class))
                .collect(toMap(this::extractEventFromAnnotation, this::createEventDeserializer));
    }

    private Class<? extends Event> extractEventFromAnnotation(Class<? extends EventDeserializer> deserializerClass) {
        try {
            return deserializerClass.getAnnotation(EventDeserializerMapping.class).value();
        } catch (Exception e) {
            LOGGER.warn("Failed to extract event for event deserializer class ({})", deserializerClass.getSimpleName());
            throw new EventDispatcherStateException(e);
        }
    }

    private EventDeserializer createEventDeserializer(Class<? extends EventDeserializer> deserializerClass) {
        try {
            return deserializerClass.newInstance();
        } catch (Exception e) {
            LOGGER.warn("Failed to createState event deserializer ({})", deserializerClass.getSimpleName());
            throw new EventDispatcherStateException(e);
        }
    }
}
