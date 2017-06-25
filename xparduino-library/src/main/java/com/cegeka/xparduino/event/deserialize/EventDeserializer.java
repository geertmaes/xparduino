package com.cegeka.xparduino.event.deserialize;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.serialize.SerializedEvent;

public interface EventDeserializer<T extends Event> {

    T deserialize(SerializedEvent event);
}
