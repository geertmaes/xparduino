package com.cegeka.xparduino.event.serialize;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.event.EventCode;

public class SerializedEvent {

    private final String body;
    private final EventCode eventCode;
    private final Component component;

    public SerializedEvent(String body, EventCode eventCode, Component component) {
        this.body = body;
        this.eventCode = eventCode;
        this.component = component;
    }

    public String body() {
        return body;
    }

    public EventCode eventCode() {
        return eventCode;
    }

    public Component component() {
        return component;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SerializedEvent that = (SerializedEvent) o;

        if (!body.equals(that.body)) return false;
        if (eventCode != that.eventCode) return false;
        return component.equals(that.component);
    }

    @Override
    public int hashCode() {
        int result = body.hashCode();
        result = 31 * result + eventCode.hashCode();
        result = 31 * result + component.hashCode();
        return result;
    }
}
