package com.cegeka.xparduino.event.mapper;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.serialized.SerializedEvent;
import com.cegeka.xparduino.event.serialized.SerializedEventFactory;
import com.cegeka.xparduino.event.mapper.deserializer.EventDeserializer;
import com.cegeka.xparduino.event.mapper.serializer.EventSerializer;

public class EventMapper {

    private final EventSerializer eventSerializer;
    private final EventDeserializer eventDeserializer;
    private final SerializedEventFactory serializedEventFactory;

    public EventMapper(EventSerializer eventSerializer,
                       EventDeserializer eventDeserializer) {
        this.eventSerializer = eventSerializer;
        this.eventDeserializer = eventDeserializer;
        this.serializedEventFactory = new SerializedEventFactory();
    }

    public Event mapToEvent(SerializedEvent event) {
        return eventDeserializer.deserialize(event);
    }

    public SerializedEvent mapToSerializedEvent(Event event) {
        return eventSerializer.serialize(event);
    }

    public SerializedEvent mapToSerializedEvent(String event) {
        return serializedEventFactory.create(event);
    }

}
