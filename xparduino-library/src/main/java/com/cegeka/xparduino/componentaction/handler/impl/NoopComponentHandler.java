package com.cegeka.xparduino.componentaction.handler.impl;

import com.cegeka.xparduino.componentaction.ComponentAction;
import com.cegeka.xparduino.componentaction.handler.ComponentHandler;
import com.cegeka.xparduino.event.Event;

import java.util.stream.Stream;

public class NoopComponentHandler implements ComponentHandler {

    @Override
    public Stream<Event> handle(ComponentAction componentAction) {
        return Stream.empty();
    }
}
