package com.cegeka.xparduino.event.serialized.serializer;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventSerializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

import java.util.Map;

public class SerializedEventSerializer {

    private final Map<Class<? extends Event>, EventSerializer> serializerMap;

    public SerializedEventSerializer(Map<Class<? extends Event>, EventSerializer> serializerMap) {
        this.serializerMap = serializerMap;
    }

    @SuppressWarnings("unchecked")
    public SerializedEvent serialize(Event event) {
        return serializerMap.get(event.getClass()).serialize(event);
    }

}
