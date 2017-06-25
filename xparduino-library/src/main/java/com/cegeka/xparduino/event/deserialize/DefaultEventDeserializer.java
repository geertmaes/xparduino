package com.cegeka.xparduino.event.deserialize;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.serialize.SerializedEvent;

import java.lang.reflect.Constructor;

import static java.lang.String.format;

public class DefaultEventDeserializer<T extends Event> implements EventDeserializer<T> {

    private static final String INSTANTIATION_EXCEPTION = "Failed to instantiate event (%s) with pin constructor";

    private final Class<T> eventClass;

    public DefaultEventDeserializer(Class<T> eventClass) {
        this.eventClass = eventClass;
    }

    @Override
    public T deserialize(SerializedEvent event) {
        try {
            Constructor<T> constructorWithPin = eventClass.getConstructor(Integer.TYPE);
            return constructorWithPin.newInstance(event.component().getPin());
        } catch (Exception e) {
           throw new EventDeserializationException(format(INSTANTIATION_EXCEPTION, event.eventCode()), e);
        }
    }
}
