package com.cegeka.xparduino.event.mapper;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.serialized.SerializedEvent;
import com.cegeka.xparduino.event.serialized.SerializedEventFactory;
import com.cegeka.xparduino.event.serialized.deserializer.SerializedEventDeserializer;
import com.cegeka.xparduino.event.serialized.serializer.SerializedEventSerializer;

public class EventMapper {

    private final SerializedEventFactory serializedEventFactory;
    private final SerializedEventSerializer serializedEventSerializer;
    private final SerializedEventDeserializer serializedEventDeserializer;

    public EventMapper(SerializedEventSerializer serializedEventSerializer,
                       SerializedEventDeserializer serializedEventDeserializer) {
        this.serializedEventSerializer = serializedEventSerializer;
        this.serializedEventDeserializer = serializedEventDeserializer;
        this.serializedEventFactory = new SerializedEventFactory();
    }

    public Event mapToEvent(SerializedEvent event) {
        return serializedEventDeserializer.deserialize(event);
    }

    public SerializedEvent mapToSerializedEvent(Event event) {
        return serializedEventSerializer.serialize(event);
    }

    public SerializedEvent mapToSerializedEvent(String event) {
        return serializedEventFactory.create(event);
    }

}
