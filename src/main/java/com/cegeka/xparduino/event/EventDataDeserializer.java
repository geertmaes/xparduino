package com.cegeka.xparduino.event;

import com.cegeka.xparduino.event.serialized.SerializedEvent;

@FunctionalInterface
public interface EventDataDeserializer<T extends Event> {

    T deserialize(SerializedEvent event);
}
