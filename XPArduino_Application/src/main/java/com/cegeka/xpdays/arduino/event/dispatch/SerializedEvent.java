package com.cegeka.xpdays.arduino.event.dispatch;

import com.cegeka.xpdays.arduino.component.Component;
import com.cegeka.xpdays.arduino.event.EventCode;

public class SerializedEvent {

    private final String body;
    private final EventCode eventCode;
    private final Component component;

    public SerializedEvent(String body, EventCode eventCode, Component component) {
        this.body = body;
        this.eventCode = eventCode;
        this.component = component;
    }

    public String getBody() {
        return body;
    }

    public EventCode getEventCode() {
        return eventCode;
    }

    public Component getComponent() {
        return component;
    }
}
