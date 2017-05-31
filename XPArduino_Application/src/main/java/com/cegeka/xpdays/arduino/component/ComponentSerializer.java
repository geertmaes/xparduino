package com.cegeka.xpdays.arduino.component;

public class ComponentSerializer {

    public String serialize(Component component) {
        return String.format("%s:%s", component.getType().getValue(), component.getPin());
    }
}
