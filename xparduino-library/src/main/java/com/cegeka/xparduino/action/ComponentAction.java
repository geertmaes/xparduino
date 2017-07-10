package com.cegeka.xparduino.action;

import com.cegeka.xparduino.component.Component;

public class ComponentAction {

    private final Action action;
    private final Component component;

    public ComponentAction(Action action, Component component) {
        this.action = action;
        this.component = component;
    }

    public Action getAction() {
        return action;
    }

    public Component getComponent() {
        return component;
    }

    public int getPin() {
        return component.getPin();
    }

}
