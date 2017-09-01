package com.cegeka.xparduino.component.action;

import com.cegeka.xparduino.component.Component;

public class ComponentAction {

    private final String action;
    private final Component component;

    ComponentAction(String action, Component component) {
        this.action = action;
        this.component = component;
    }

    public String action() {
        return action;
    }

    public Component component() {
        return component;
    }

    public int pin() {
        return component.getPin();
    }

}
