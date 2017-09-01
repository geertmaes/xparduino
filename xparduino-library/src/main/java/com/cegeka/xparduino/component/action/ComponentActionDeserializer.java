package com.cegeka.xparduino.component.action;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.serialization.ComponentDeserializer;

import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ComponentActionDeserializer {

    private static final String COMPONENT_ACTION_SEPARATOR = ",";
    private static final Pattern COMPONENT_ACTION_FORMAT = Pattern.compile("^<\\d*:\\d*,.*>$");
    private static final String INVALID_FORMAT = "Component action (%s) has invalid format";

    private final ComponentDeserializer componentDeserializer = new ComponentDeserializer();

    public ComponentAction deserialize(String message) {
        requireNonNull(message);
        validateFormat(message);

        String[] componentActionParts = removeIdentifiers(message).split(COMPONENT_ACTION_SEPARATOR);

        String action = componentActionParts[1];
        Component component = componentDeserializer.deserialize(componentActionParts[0]);

        return new ComponentAction(action, component);
    }

    private String removeIdentifiers(String message) {
        return message.substring(1, message.length() - 1);
    }

    private void validateFormat(String message) {
        if (!COMPONENT_ACTION_FORMAT.matcher(message).matches()) {
            throw new ComponentActionFormatException(format(INVALID_FORMAT, message));
        }
    }

}
