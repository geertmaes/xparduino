package com.cegeka.xparduino.component.event;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.impl.baseled.BaseLedEvent;

import java.util.stream.Stream;

public class BaseLedEventFactory implements ComponentEventFactory {

    private static final String ON_ACTION = "on";
    private static final String OFF_ACTION = "off";

    @Override
    public Stream<Event> create(int pin, String action) {
        if (isOnAction(action))
            return Stream.of(new BaseLedEvent(pin, true));
        if (isOffAction(action))
            return Stream.of(new BaseLedEvent(pin, false));
        return Stream.empty();
    }

    private boolean isOnAction(String action) {
        return action.equalsIgnoreCase(ON_ACTION);
    }

    private boolean isOffAction(String action) {
        return action.equalsIgnoreCase(OFF_ACTION);
    }

}
