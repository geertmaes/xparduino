package com.cegeka.xparduino.componentaction.handler;

import com.cegeka.xparduino.componentaction.ComponentAction;
import com.cegeka.xparduino.event.Event;

import java.util.stream.Stream;

@FunctionalInterface
public interface ComponentHandler {

    Stream<Event> handle(ComponentAction componentAction);
}
