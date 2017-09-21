package com.cegeka.xparduino.event.mapper;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.mapper.deserializer.EventDeserializer;
import com.cegeka.xparduino.event.mapper.serializer.EventSerializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;
import com.cegeka.xparduino.event.serialized.SerializedEventFactory;

public class EventMapper {

    private final EventSerializer eventSerializer;
    private final EventDeserializer eventDeserializer;
    private final SerializedEventFactory serializedEventFactory;

    EventMapper(EventSerializer eventSerializer,
                EventDeserializer eventDeserializer) {
        this.eventSerializer = eventSerializer;
        this.eventDeserializer = eventDeserializer;
        this.serializedEventFactory = new SerializedEventFactory();
    }

    public Event toEvent(String event) {
        return eventDeserializer.deserialize(toSerializedEvent(event));
    }

    public Event toEvent(SerializedEvent event) {
        return eventDeserializer.deserialize(event);
    }

    public SerializedEvent toSerializedEvent(String event) {
        return serializedEventFactory.create(event);
    }

    public SerializedEvent toSerializedEvent(Event event) {
        return eventSerializer.serialize(event);
    }

}
