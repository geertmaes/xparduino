package com.cegeka.xparduino.component.mapper;

import com.cegeka.xparduino.component.Component;

public class ComponentMapper {

    private final ComponentSerializer serializer;
    private final ComponentDeserializer deserializer;

    public ComponentMapper() {
        serializer = new ComponentSerializer();
        deserializer = new ComponentDeserializer();
    }

    public String serialize(Component component) {
        return serializer.serialize(component);
    }

    public Component deserialize(String message) {
        return deserializer.deserialize(message);
    }

}
