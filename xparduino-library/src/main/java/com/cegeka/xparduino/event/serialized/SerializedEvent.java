package com.cegeka.xparduino.event.serialized;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.event.EventCode;

public class SerializedEvent {

    private final EventCode eventCode;
    private final String eventBody;
    private final Component component;

    public SerializedEvent(EventCode eventCode,
                           String eventBody,
                           Component component) {
        this.eventCode = eventCode;
        this.eventBody = eventBody;
        this.component = component;
    }

    public EventCode eventCode() {
        return eventCode;
    }

    public String eventBody() {
        return eventBody;
    }

    public Component component() {
        return component;
    }

    public String toString() {
        return String.format("<%d:%d,%d,%s>",
                component.getType().getValue(), component.getPin(), eventCode.getValue(), eventBody);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SerializedEvent that = (SerializedEvent) o;

        if (!eventBody.equals(that.eventBody)) return false;
        if (!eventCode.equals(that.eventCode)) return false;
        return component.equals(that.component);
    }

    @Override
    public int hashCode() {
        int result = eventBody.hashCode();
        result = 31 * result + eventCode.hashCode();
        result = 31 * result + component.hashCode();
        return result;
    }
}
