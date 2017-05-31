package com.cegeka.xpdays.arduino.event.dispatch;

import com.cegeka.xpdays.arduino.event.*;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.reflections.ReflectionUtils.getMethods;

@SuppressWarnings("unchecked")
public class EventDispatcher {

    private static final String PACKAGE_NAME = "com.cegeka.xpdays.arduino";
    private static final Logger LOGGER = LoggerFactory.getLogger(EventDispatcher.class);

    private final Set<EventListener> eventListeners;
    private final Set<Class<? extends Event>> eventClasses;
    private final SerializedEventFactory serializedEventFactory;

    public EventDispatcher() {
        this.eventListeners = new HashSet<>();
        this.eventClasses = new Reflections(PACKAGE_NAME).getSubTypesOf(Event.class);
        this.serializedEventFactory = new SerializedEventFactory();
    }

    public void registerEventListener(EventListener listener) {
        eventListeners.add(listener);
    }

    public void dispatch(String payload) throws EventDispatchingException {
        try {
            SerializedEvent serializedEvent
                    = serializedEventFactory.create(payload);

            dispatch(serializedEvent);
        } catch (Exception e) {
            LOGGER.warn("Failed to dispatch event with payload ({})", payload, e);
            throw new EventDispatchingException(e);
        }
    }

    private void dispatch(SerializedEvent serializedEvent) {
        Set<Class<? extends Event>> eventClasses
                = getEventClassesByEventCode(serializedEvent.getEventCode());

        eventClasses.forEach(eventClass -> {
            EventDeserializer deserializer = createEventDeserializer(eventClass);
            Event event = deserializer.deserialize(serializedEvent);

            dispatchEventToListeners(event);
        });
    }

    private Set<Class<? extends Event>> getEventClassesByEventCode(EventCode eventCode) {
        return eventClasses.stream()
                .filter(eventClass -> classHasEventCode(eventClass, eventCode))
                .collect(Collectors.toSet());
    }

    private boolean classHasEventCode(Class<? extends Event> clazz, EventCode eventCode) {
        return clazz.getAnnotation(EventMapping.class).value() == eventCode;
    }

    private EventDeserializer createEventDeserializer(Class<? extends Event> clazz) {
        try {
            EventMapping annotation = clazz.getAnnotation(EventMapping.class);

            return annotation.mapper().isAssignableFrom(DefaultEventDeserializer.class)
                    ? new DefaultEventDeserializer(clazz)
                    : annotation.mapper().newInstance();
        } catch (Exception e) {
            LOGGER.warn("Failed to create mapper for event class ({})", clazz.getSimpleName());
            throw new RuntimeException(e);
        }
    }

    private void dispatchEventToListeners(Event event) {
        eventListeners.forEach(listener -> {
            Set<Method> methods = getMatchingMethods(listener, event.getClass());
            methods.forEach(method -> invokeMethod(listener, method, event));
        });
    }

    private Set<Method> getMatchingMethods(EventListener listener, Class<? extends Event> clazz) {
        return getMethods(listener.getClass(), method -> classIsAssignableToMethod(method, clazz));
    }

    private boolean classIsAssignableToMethod(Method method, Class<?> type) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        return parameterTypes.length > 0 && parameterTypes[0].isAssignableFrom(type);
    }

    private void invokeMethod(EventListener listener, Method method, Event event) {
        try {
            method.invoke(listener, event);
        } catch (Exception e) {
            LOGGER.warn("Failed to invoke method ({}) of class ({})", method.getName(), listener.getClass().getSimpleName(), e);
        }
    }
}
