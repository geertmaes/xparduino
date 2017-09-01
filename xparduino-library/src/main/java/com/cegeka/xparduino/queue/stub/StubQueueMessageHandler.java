package com.cegeka.xparduino.queue.stub;

import com.cegeka.xparduino.component.action.ComponentAction;
import com.cegeka.xparduino.component.action.ComponentActionDeserializer;
import com.cegeka.xparduino.component.event.ComponentEventFactory;
import com.cegeka.xparduino.component.event.ComponentEventFactoryMapper;
import com.cegeka.xparduino.event.Event;

import java.util.stream.Stream;

public class StubQueueMessageHandler {

    private final ComponentEventFactoryMapper componentEventFactoryMapper;
    private final ComponentActionDeserializer componentActionDeserializer;

    public StubQueueMessageHandler() {
        componentEventFactoryMapper = new ComponentEventFactoryMapper();
        componentActionDeserializer = new ComponentActionDeserializer();
    }

    public Stream<Event> handle(String message) {
        ComponentAction componentAction = componentActionDeserializer.deserialize(message);
        ComponentEventFactory eventFactory = getEventFactory(componentAction);

        return eventFactory.create(componentAction.pin(), componentAction.action());
    }

    private ComponentEventFactory getEventFactory(ComponentAction componentAction) {
        return componentEventFactoryMapper.toFactory(componentAction.component().getType());
    }

}
