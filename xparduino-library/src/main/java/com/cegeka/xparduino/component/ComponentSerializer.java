package com.cegeka.xparduino.component;

public class ComponentSerializer {

    private static final String COMPONENT_FORMAT = "%s:%s";

    public String serialize(Component component) {
        return String.format(COMPONENT_FORMAT, component.getType().getValue(), component.getPin());
    }
}
