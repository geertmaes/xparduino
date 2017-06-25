package com.cegeka.xparduino.event.serialize;

import com.cegeka.xparduino.event.EventCode;
import com.cegeka.xparduino.event.deserialize.EventDeserializationException;
import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.ComponentDeserializer;

import java.util.regex.Pattern;

import static java.lang.String.format;

public class SerializedEventFactory {

    private static final String EVENT_SEPARATOR = ",";
    private final static Pattern EVENT_FORMAT = Pattern.compile("^<\\d*:\\d*,\\d*,.*>$");

    private final ComponentDeserializer componentDeserializer;

    public SerializedEventFactory() {
        componentDeserializer = new ComponentDeserializer();
    }

    public SerializedEvent create(String event) {
        validateEventFormat(event);

        String eventData = removeEventMarkers(event);
        String[] eventParts = eventData.split(EVENT_SEPARATOR);

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

    private String removeEventMarkers(String payload) {
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
