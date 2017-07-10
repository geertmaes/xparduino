package com.cegeka.xparduino.action.impl;

import com.cegeka.xparduino.action.ComponentAction;
import com.cegeka.xparduino.action.ComponentActionHandler;
import com.cegeka.xparduino.event.Event;

import java.util.stream.Stream;

public class NoopComponentActionHandler implements ComponentActionHandler {

    @Override
    public Stream<Event> handle(ComponentAction componentAction) {
        return Stream.empty();
    }
}
