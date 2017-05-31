package com.cegeka.xpdays.arduino.event;

import com.cegeka.xpdays.arduino.event.dispatch.SerializedEvent;

public class DefaultEventDeserializer<T extends Event> implements EventDeserializer<T> {

    private final Class<T> clazz;

    public DefaultEventDeserializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T deserialize(SerializedEvent event) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }
}
