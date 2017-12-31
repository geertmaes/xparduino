package com.cegeka.xparduino.event.mapper;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.mapper.deserializer.EventDeserializer;
import com.cegeka.xparduino.event.mapper.deserializer.EventDeserializerFactory;
import com.cegeka.xparduino.event.mapper.serializer.EventSerializer;
import com.cegeka.xparduino.event.mapper.serializer.EventSerializerFactory;

import java.util.Set;

public class EventMapperFactory {

    private final EventSerializerFactory eventSerializerFactory
            = new EventSerializerFactory();
    private final EventDeserializerFactory eventDeserializerFactory
            = new EventDeserializerFactory();

    public EventMapper create(Set<Class<? extends Event>> events) {
        EventSerializer serializer
                = eventSerializerFactory.create(events);
        EventDeserializer deserializer
                = eventDeserializerFactory.create(events);

        return new EventMapper(serializer, deserializer);
    }
}
