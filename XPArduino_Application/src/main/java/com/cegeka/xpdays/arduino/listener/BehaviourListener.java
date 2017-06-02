package com.cegeka.xpdays.arduino.listener;

import com.cegeka.xpdays.arduino.Arduino;
import com.cegeka.xpdays.arduino.event.dispatch.EventListener;

public abstract class BehaviourListener implements EventListener {

    private final Arduino arduino;

    public BehaviourListener(Arduino arduino) {
        this.arduino = arduino;
    }

    public Arduino arduino() {
        return arduino;
    }
}
