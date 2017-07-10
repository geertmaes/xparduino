package com.cegeka.xparduino.action;

import com.cegeka.xparduino.event.Event;

import java.util.stream.Stream;

@FunctionalInterface
public interface ComponentActionHandler {

    Stream<Event> handle(ComponentAction componentAction);
}
