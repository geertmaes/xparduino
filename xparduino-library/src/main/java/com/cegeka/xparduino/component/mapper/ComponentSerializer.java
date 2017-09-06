package com.cegeka.xparduino.component.mapper;

import com.cegeka.xparduino.component.Component;

import static java.util.Objects.requireNonNull;

class ComponentSerializer {

    private static final String COMPONENT_FORMAT = "%s:%s";

    String serialize(Component component) {
        requireNonNull(component);
        return String.format(COMPONENT_FORMAT, component.getType().getValue(), component.getPin().value());
    }

}
