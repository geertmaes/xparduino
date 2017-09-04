package com.cegeka.xparduino.command.impl;

import com.cegeka.xparduino.command.AbstractCommand;
import com.cegeka.xparduino.component.ComponentType;

public class BaseLedCommand extends AbstractCommand {

    private final boolean emitting;

    public BaseLedCommand(int pin, boolean emitting) {
        super(pin);
        this.emitting = emitting;
    }

    @Override
    public String getAction() {
        return emitting ? "ON" : "OFF";
    }

    @Override
    protected ComponentType getComponentType() {
        return ComponentType.BASE_LED;
    }

}
