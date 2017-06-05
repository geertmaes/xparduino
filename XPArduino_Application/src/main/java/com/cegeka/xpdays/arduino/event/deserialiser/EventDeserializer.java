package com.cegeka.xpdays.arduino.event.deserialiser;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.dispatch.SerializedEvent;

public interface EventDeserializer<T extends Event> {

    T deserialize(SerializedEvent event);
}
