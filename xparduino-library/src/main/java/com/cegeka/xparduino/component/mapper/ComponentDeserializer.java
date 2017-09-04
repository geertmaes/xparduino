package com.cegeka.xparduino.component.mapper;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.ComponentType;

import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

class ComponentDeserializer {

    private static final String COMPONENT_PART_SEPARATOR = ":";
    private static final Pattern COMPONENT_FORMAT = Pattern.compile("^\\d*:\\d*$");
    private static final String INVALID_FORMAT = "Component (%s) has invalid format";

    Component deserialize(String message) {
        requireNonNull(message);
        validateFormat(message);

        String[] componentParts = message.split(COMPONENT_PART_SEPARATOR);

        return new Component(
                extractPin(componentParts),
                extractComponentType(componentParts)
        );
    }

    private void validateFormat(String message) {
        if (!COMPONENT_FORMAT.matcher(message).matches()) {
            throw new ComponentFormatException(format(INVALID_FORMAT, message));
        }
    }

    private int extractPin(String[] componentParts) {
        return Integer.valueOf(componentParts[1]);
    }

    private ComponentType extractComponentType(String[] componentParts) {
        int value = Integer.valueOf(componentParts[0]);
        return ComponentType.valueOf(value);
    }

}
