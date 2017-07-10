package com.cegeka.xparduino.component.serialization;

import com.cegeka.xparduino.component.Component;

import static java.util.Objects.requireNonNull;

public class ComponentSerializer {

    private static final String COMPONENT_FORMAT = "%s:%s";

    public String serialize(Component component) {
        requireNonNull(component);
        return String.format(COMPONENT_FORMAT, component.getType().getValue(), component.getPin());
    }
}
