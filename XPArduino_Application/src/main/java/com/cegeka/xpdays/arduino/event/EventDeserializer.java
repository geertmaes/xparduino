package com.cegeka.xpdays.arduino.event;

import com.cegeka.xpdays.arduino.event.dispatch.SerializedEvent;

public interface EventDeserializer<T extends Event> {

    T deserialize(SerializedEvent event);
}
