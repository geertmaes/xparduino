package com.cegeka.xparduino.action.impl;

import com.cegeka.xparduino.action.ComponentAction;
import com.cegeka.xparduino.action.ComponentActionHandler;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.impl.baseled.BaseLedEvent;

import java.util.stream.Stream;

public class BaseLedComponentActionHandler implements ComponentActionHandler {

    @Override
    public Stream<Event> handle(ComponentAction action) {
        boolean emitting = action.getAction()
                .value()
                .equalsIgnoreCase("on");

        return Stream.of(new BaseLedEvent(action.getPin(), emitting));
    }
}
