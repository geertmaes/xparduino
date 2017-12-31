package com.cegeka.xparduino.event.serialized;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.mapper.ComponentMapper;
import com.cegeka.xparduino.event.EventCode;

import java.util.regex.Pattern;

import static java.lang.String.format;

public class SerializedEventFactory {

    private static final String EVENT_PART_SEPARATOR = ",";
    private static final Pattern EVENT_FORMAT = Pattern.compile("^<\\d*:\\d*,.*,.*>$");
    private static final String INVALID_FORMAT = "Event (%s) has invalid format";

    private final ComponentMapper componentMapper = new ComponentMapper();

    public SerializedEvent create(String event) {
        validateEventFormat(event);

        String eventData = removeEventIdentifiers(event);
        String[] eventParts = eventData.split(EVENT_PART_SEPARATOR);

        return new SerializedEvent(
                extractCode(eventParts),
                extractBody(eventParts),
                extractComponent(eventParts));
    }

    private void validateEventFormat(String payload) {
        if (!EVENT_FORMAT.matcher(payload).matches()) {
            throw new SerializedEventFormatException(format(INVALID_FORMAT, payload));
        }
    }

    private String removeEventIdentifiers(String event) {
        return event.substring(1, event.length() - 1);
    }

    private Component extractComponent(String[] eventParts) {
        return componentMapper.deserialize(eventParts[0]);
    }

    private EventCode extractCode(String[] eventParts) {
        return EventCode.valueOf(Integer.parseInt(eventParts[1]));
    }

    private String extractBody(String[] eventParts) {
        return eventParts[2];
    }
}
