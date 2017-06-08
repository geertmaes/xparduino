package com.cegeka.xpdays.arduino.state;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventListener;
import com.cegeka.xpdays.arduino.event.Handle;

import java.util.Collection;

import static com.cegeka.xpdays.arduino.utils.ReflectionUtils.getMethodsWithParameterType;
import static com.cegeka.xpdays.arduino.utils.ReflectionUtils.invokeMethod;

public class ComponentStateEventDispatcher implements EventListener {

    private final Collection<ComponentState> componentStates;

    ComponentStateEventDispatcher(Collection<ComponentState> componentStates) {
        this.componentStates = componentStates;
    }

    @Handle
    public void on(Event event) {
        componentStates.stream()
                .filter(state -> stateHasPin(event, state))
                .forEach(state -> dispatchEventToState(state, event));
    }

    private boolean stateHasPin(Event event, ComponentState state) {
        return state.getPin() == event.getPin();
    }

    private void dispatchEventToState(ComponentState state, Event event) {
        getMethodsWithParameterType(state, event.getClass())
                .forEach(method -> invokeMethod(state, method, event));
        state.triggerStateChange();
    }
}
