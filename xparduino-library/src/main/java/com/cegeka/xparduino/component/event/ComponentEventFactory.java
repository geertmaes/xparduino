package com.cegeka.xparduino.component.event;

import com.cegeka.xparduino.event.Event;

import java.util.stream.Stream;

public interface ComponentEventFactory {

    Stream<Event> create(int pin, String action);
}
