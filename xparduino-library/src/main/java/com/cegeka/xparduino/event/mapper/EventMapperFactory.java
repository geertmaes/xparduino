package com.cegeka.xparduino.event.mapper;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.serialized.deserializer.SerializedEventDeserializer;
import com.cegeka.xparduino.event.serialized.deserializer.SerializedEventDeserializerFactory;
import com.cegeka.xparduino.event.serialized.serializer.SerializedEventSerializer;
import com.cegeka.xparduino.event.serialized.serializer.SerializedEventSerializerFactory;

import java.util.Set;

public class EventMapperFactory {

    private final SerializedEventSerializerFactory serializedEventSerializerFactory
            = new SerializedEventSerializerFactory();
    private final SerializedEventDeserializerFactory serializedEventDeserializerFactory
            = new SerializedEventDeserializerFactory();

    public EventMapper create(Set<Class<? extends Event>> events) {
        SerializedEventSerializer serializer
                = serializedEventSerializerFactory.create(events);
        SerializedEventDeserializer deserializer
                = serializedEventDeserializerFactory.create(events);

        return new EventMapper(serializer, deserializer);
    }
}
