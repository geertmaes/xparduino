package com.cegeka.xparduino.componentaction.handler;

import com.cegeka.xparduino.componentaction.Action;
import com.cegeka.xparduino.componentaction.ComponentAction;
import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.event.Event;

import java.util.stream.Stream;

import static java.lang.String.format;

public abstract class AbstractComponentHandler implements ComponentHandler {

    private static final String TYPE_MISMATCH = "Component type mismatch. Expected (%s), actual (%s)";
    private static final String ERROR_HANDLING = "Failed to handle action (%s) for component handler (%s)";

    private final ComponentType componentType;

    protected AbstractComponentHandler(ComponentType componentType) {
        this.componentType = componentType;
    }

    public Stream<Event> handle(ComponentAction componentAction) {
        validateComponent(componentAction.getComponent());
        return handleAction(componentAction);
    }

    private Stream<Event> handleAction(ComponentAction componentAction) {
        try {
            return handle(componentAction.getPin(), componentAction.getAction());
        } catch (Exception e) {
            throw new ComponentHandlingException(format(ERROR_HANDLING, componentAction.getAction(), componentType));
        }
    }

    private void validateComponent(Component component) {
        if (component.getType() != componentType) {
            throw new ComponentHandlingException(format(TYPE_MISMATCH, componentType, component.getType()));
        }
    }

    protected abstract Stream<Event> handle(int pin, Action action);

}
