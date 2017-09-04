package com.cegeka.xparduino.queue.stub;

import com.cegeka.xparduino.command.serialized.SerializedCommand;
import com.cegeka.xparduino.command.serialized.SerializedCommandFactory;
import com.cegeka.xparduino.component.event.ComponentEventFactory;
import com.cegeka.xparduino.component.event.ComponentEventFactoryMapper;
import com.cegeka.xparduino.event.Event;

import java.util.stream.Stream;

public class StubQueueMessageHandler {

    private final SerializedCommandFactory serializedCommandFactory;
    private final ComponentEventFactoryMapper componentEventFactoryMapper;

    public StubQueueMessageHandler() {
        serializedCommandFactory = new SerializedCommandFactory();
        componentEventFactoryMapper = new ComponentEventFactoryMapper();
    }

    public Stream<Event> handle(String message) {
        SerializedCommand command = serializedCommandFactory.create(message);
        ComponentEventFactory eventFactory = getEventFactory(command);

        return eventFactory.create(command);
    }

    private ComponentEventFactory getEventFactory(SerializedCommand command) {
        return componentEventFactoryMapper.toFactory(command.component().getType());
    }

}
