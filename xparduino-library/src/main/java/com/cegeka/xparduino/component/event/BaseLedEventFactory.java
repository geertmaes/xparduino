package com.cegeka.xparduino.component.event;

import com.cegeka.xparduino.command.serialized.SerializedCommand;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.impl.baseled.BaseLedEvent;

import java.util.stream.Stream;

public class BaseLedEventFactory implements ComponentEventFactory {

    private static final String ON_ACTION = "on";
    private static final String OFF_ACTION = "off";

    @Override
    public Stream<Event> create(SerializedCommand command) {
        String action = command.action();
        if (isOnAction(action))
            return Stream.of(new BaseLedEvent(command.pin(), true));
        if (isOffAction(action))
            return Stream.of(new BaseLedEvent(command.pin(), false));
        return Stream.empty();
    }

    private boolean isOnAction(String action) {
        return action.equalsIgnoreCase(ON_ACTION);
    }

    private boolean isOffAction(String action) {
        return action.equalsIgnoreCase(OFF_ACTION);
    }

}
