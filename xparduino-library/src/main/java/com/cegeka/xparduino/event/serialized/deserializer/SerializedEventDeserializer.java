package com.cegeka.xparduino.event.serialized.deserializer;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventCode;
import com.cegeka.xparduino.event.EventDeserializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

import java.util.Map;

public class SerializedEventDeserializer {

    private final Map<EventCode, EventDeserializer<? extends Event>> deserializerMap;

    public SerializedEventDeserializer(Map<EventCode, EventDeserializer<? extends Event>> deserializerMap) {
        this.deserializerMap = deserializerMap;
    }

    public Event deserialize(SerializedEvent event) {
        return deserializerMap.get(event.eventCode()).deserialize(event);
    }
}
