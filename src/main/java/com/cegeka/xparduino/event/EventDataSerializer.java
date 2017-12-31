package com.cegeka.xparduino.event;

import com.cegeka.xparduino.event.serialized.SerializedEvent;

@FunctionalInterface
public interface EventDataSerializer<T extends Event> {

    SerializedEvent serialize(T event);
}
