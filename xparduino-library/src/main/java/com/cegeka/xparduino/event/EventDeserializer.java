package com.cegeka.xparduino.event;

import com.cegeka.xparduino.event.serialized.SerializedEvent;

@FunctionalInterface
public interface EventDeserializer<T extends Event> {

    T deserialize(SerializedEvent event);
}
