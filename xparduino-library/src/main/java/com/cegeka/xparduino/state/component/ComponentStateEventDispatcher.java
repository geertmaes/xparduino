package com.cegeka.xparduino.state.component;

import com.cegeka.xparduino.event.Event;

import java.util.Collection;

import static com.cegeka.xparduino.utils.ReflectionUtils.invokeEventAssignableMethods;

public class ComponentStateEventDispatcher {

    private final Collection<ComponentState> componentStates;

    public ComponentStateEventDispatcher(Collection<ComponentState> componentStates) {
        this.componentStates = componentStates;
    }

    public void dispatch(Event event) {
        componentStates.stream()
                .filter(state -> stateHasPin(event, state))
                .forEach(state -> dispatchEventToState(state, event));
    }

    private boolean stateHasPin(Event event, ComponentState state) {
        return event.getPin() == state.getPin();
    }

    private void dispatchEventToState(ComponentState state, Event event) {
        invokeEventAssignableMethods(state, event);
        state.triggerStateChange();
    }
}
