package com.cegeka.xparduino.event.dispatch;

import com.cegeka.xparduino.event.*;
import com.cegeka.xparduino.event.serialize.SerializedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

import static com.cegeka.xparduino.utils.ReflectionUtils.*;
import static java.lang.String.format;

@SuppressWarnings("unchecked")
public class EventDispatcher {

    public static EventDispatcher scanPackage(String scanPackage) {
        return new EventDispatcher(EventDispatcherState.scanPackage(scanPackage));
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(EventDispatcher.class);
    private static final String DISPATCHING_EXCEPTION = "Failed to dispatch event with payload ({})";
    private static final String NO_EVENT_SPECIFIED_EXCEPTION = "No event specified for event code (%s)";

    private final EventDispatcherState state;
    private final List<EventListener> eventListeners;

    private EventDispatcher(EventDispatcherState state) {
        this.state = state;
        this.eventListeners = new LinkedList<>();
    }

    public void registerEventListener(EventListener listener) {
        eventListeners.add(listener);
    }

    public void dispatch(SerializedEvent serializedEvent) throws EventDispatchingException {
        try {
            Event event = deserialize(serializedEvent);
            LOGGER.info("Dispatching event ({}) with body ({}) on pin ({})",
                    event.getClass().getSimpleName(), serializedEvent.body(), serializedEvent.component().getPin());
            dispatchToListeners(event);
        } catch (Exception e) {
            LOGGER.warn(DISPATCHING_EXCEPTION, serializedEvent, e);
            throw new EventDispatchingException(e);
        }
    }

    private Event deserialize(SerializedEvent serializedEvent) throws EventDispatchingException {
        EventCode eventCode = serializedEvent.eventCode();

        return state.getEventClassByEventCode(eventCode)
                .map(state::getEventDeserializerByEventClass)
                .map(deserializer -> deserializer.deserialize(serializedEvent))
                .orElseThrow(() -> new EventDispatchingException(format(NO_EVENT_SPECIFIED_EXCEPTION, eventCode)));
    }

    private void dispatchToListeners(Event event) {
        eventListeners.forEach(listener -> invokeEventAssignableMethods(listener, event));
    }
}
