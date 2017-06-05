package com.cegeka.xpdays.arduino.event.dispatch;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventListener;
import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static java.lang.reflect.Modifier.isPublic;
import static org.reflections.ReflectionUtils.getMethods;

@SuppressWarnings("unchecked")
public class EventDispatcher {

    public static EventDispatcher fromPackage(String scanPackage) {
        return new EventDispatcher(EventDispatcherState.fromPackage(scanPackage));
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(EventDispatcher.class);
    private static final String DISPATCHING_EXCEPTION = "Failed to dispatch event with payload ({})";
    private static final String METHOD_INVOCATION_EXCEPTION = "Failed to invoke method ({}) of class ({}) for parameter ({})";

    private final EventDispatcherState state;
    private final List<EventListener> eventListeners;
    private final SerializedEventFactory serializedEventFactory;

    private EventDispatcher(EventDispatcherState state) {
        this.state = state;
        this.eventListeners = new LinkedList<>();
        this.serializedEventFactory = new SerializedEventFactory();
    }

    public void registerListener(EventListener listener) {
        eventListeners.add(listener);
    }

    public void dispatch(String payload) throws EventDispatchingException {
        try {
            LOGGER.info("Dispatching payload ({})", payload);
            SerializedEvent serializedEvent
                    = serializedEventFactory.create(payload);

            dispatch(serializedEvent);
        } catch (Exception e) {
            LOGGER.warn(DISPATCHING_EXCEPTION, payload, e);
            throw new EventDispatchingException(e);
        }
    }

    private void dispatch(SerializedEvent serializedEvent) {
        Class<? extends Event> eventClass = state.getEvent(serializedEvent.eventCode());
        EventDeserializer deserializer = state.getEventSerializer(eventClass);
        Event event = deserializer.deserialize(serializedEvent);

        dispatchToListeners(event);
    }

    private void dispatchToListeners(Event event) {
        eventListeners.forEach(listener -> {
            Set<Method> methods = getMethodsWithParameterType(listener, event.getClass());
            methods.forEach(method -> invokeMethod(listener, method, event));
        });
    }

    private Set<Method> getMethodsWithParameterType(Object obj, Class parameterType) {
        return getMethods(obj.getClass(), method -> methodHasParameter(method, parameterType));
    }

    private boolean methodHasParameter(Method method, Class<?> parameterType) {
        Class<?>[] parameters = method.getParameterTypes();

        return isPublic(method.getModifiers())
                && parameters.length > 0
                && parameters[0].isAssignableFrom(parameterType);
    }

    private void invokeMethod(Object obj, Method method, Object parameters) {
        try {
            method.invoke(obj, parameters);
        } catch (Exception e) {
            LOGGER.warn(METHOD_INVOCATION_EXCEPTION, method, obj.getClass().getSimpleName(), parameters, e);
        }
    }
}
