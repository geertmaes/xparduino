package com.cegeka.xparduino.component;

public class ComponentDeserializer {

    private static final String COMPONENT_SEPARATOR = ":";

    public Component deserialize(String message) {
        String[] componentParts = message.split(COMPONENT_SEPARATOR);

        return new Component(
                extractPin(componentParts),
                extractComponentType(componentParts)
        );
    }

    private int extractPin(String[] componentParts) {
        return Integer.valueOf(componentParts[1]);
    }

    private ComponentType extractComponentType(String[] componentParts) {
        int value = Integer.valueOf(componentParts[0]);
        return ComponentType.valueOf(value);
    }
}
