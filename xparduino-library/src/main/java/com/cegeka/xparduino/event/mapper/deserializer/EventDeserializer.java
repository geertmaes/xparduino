package com.cegeka.xparduino.event.mapper.deserializer;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventCode;
import com.cegeka.xparduino.event.EventDataDeserializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class EventDeserializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventDeserializer.class);

    private final Map<EventCode, EventDataDeserializer<? extends Event>> deserializerMap;

    EventDeserializer(Map<EventCode, EventDataDeserializer<? extends Event>> deserializerMap) {
        this.deserializerMap = deserializerMap;
    }

    public Event deserialize(SerializedEvent event) {
        try {
            return deserializerMap.get(event.eventCode()).deserialize(event);
        } catch (Exception e) {
            LOGGER.warn("Failed to deserialize event ({})", event);
            throw new EventDeserializationException(e);
        }
    }
}
