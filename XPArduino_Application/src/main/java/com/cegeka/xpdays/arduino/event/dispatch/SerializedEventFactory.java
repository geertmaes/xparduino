package com.cegeka.xpdays.arduino.event.dispatch;

import com.cegeka.xpdays.arduino.component.Component;
import com.cegeka.xpdays.arduino.component.ComponentDeserializer;
import com.cegeka.xpdays.arduino.event.EventCode;

import java.util.regex.Pattern;

import static java.lang.String.format;

public class SerializedEventFactory {

    private final static Pattern EVENT_FORMAT = Pattern.compile("^<\\d:\\d,\\d,.*>$");

    private final ComponentDeserializer componentDeserializer;

    public SerializedEventFactory() {
        componentDeserializer = new ComponentDeserializer();
    }

    public SerializedEvent create(String payload) {
        validateEventFormat(payload);

        String event = removeBoundaries(payload);
        String[] eventParts = event.split(",");

        return new SerializedEvent(
                extractBody(eventParts),
                extractEventCode(eventParts),
                extractComponent(eventParts)
        );
    }

    private void validateEventFormat(String payload) {
        if (!EVENT_FORMAT.matcher(payload).matches()) {
            throw new EventDeserializationException(format("Event (%s) has invalid format", payload));
        }
    }

    private String removeBoundaries(String payload) {
        return payload.substring(1, payload.length() - 1);
    }

    private Component extractComponent(String[] eventParts) {
        return componentDeserializer.deserialize(eventParts[0]);
    }

    private EventCode extractEventCode(String[] eventParts) {
        int type = Integer.valueOf(eventParts[1]);
        return EventCode.valueOf(type);
    }

    private String extractBody(String[] eventParts) {
        return eventParts[2];
    }
}
