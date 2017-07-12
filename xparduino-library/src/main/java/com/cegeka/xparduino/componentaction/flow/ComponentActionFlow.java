package com.cegeka.xparduino.componentaction.flow;

import com.cegeka.xparduino.componentaction.ComponentAction;
import com.cegeka.xparduino.componentaction.ComponentActionDeserializer;
import com.cegeka.xparduino.componentaction.handler.ComponentHandler;
import com.cegeka.xparduino.componentaction.handler.ComponentHandlerFactory;
import com.cegeka.xparduino.event.Event;

import java.util.stream.Stream;

public class ComponentActionFlow {

    private final ComponentHandlerFactory componentHandlerFactory;
    private final ComponentActionDeserializer componentActionDeserializer;

    public ComponentActionFlow() {
        componentHandlerFactory = new ComponentHandlerFactory();
        componentActionDeserializer = new ComponentActionDeserializer();
    }

    public Stream<Event> handle(String message) {
        ComponentAction componentAction = componentActionDeserializer.deserialize(message);
        ComponentHandler componentHandler = componentHandlerFactory.create(componentAction.getComponent());

        return componentHandler.handle(componentAction);
    }

}
