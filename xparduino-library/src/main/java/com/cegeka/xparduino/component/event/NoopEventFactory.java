package com.cegeka.xparduino.component.event;

import com.cegeka.xparduino.command.serialized.SerializedCommand;
import com.cegeka.xparduino.event.Event;

import java.util.stream.Stream;

public class NoopEventFactory implements ComponentEventFactory {

    @Override
    public Stream<Event> create(SerializedCommand command) {
        return Stream.empty();
    }

}
