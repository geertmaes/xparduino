package com.cegeka.xpdays.arduino.state;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventListener;
import com.cegeka.xpdays.arduino.event.Handle;
import com.cegeka.xpdays.arduino.utils.ReflectionUtils;

import java.util.Collection;

public class ComponentStateEventDispatcher implements EventListener {

    private final ArduinoState arduinoState;

    ComponentStateEventDispatcher(ArduinoState arduinoState) {
        this.arduinoState = arduinoState;
    }

    @Handle
    public void on(Event event) {
        Collection<ComponentState> componentStates = arduinoState.getComponentStates();

        componentStates.stream()
                .filter(state -> stateHasPin(event, state))
                .forEach(state -> dispatch(state, event));
    }

    private boolean stateHasPin(Event event, ComponentState state) {
        return state.getPin() == event.getPin();
    }

    private void dispatch(ComponentState state, Event event) {
        ReflectionUtils.getMethodsWithParameterType(state, event.getClass())
                .forEach(method -> ReflectionUtils.invokeMethod(state, method, event));
    }
}
