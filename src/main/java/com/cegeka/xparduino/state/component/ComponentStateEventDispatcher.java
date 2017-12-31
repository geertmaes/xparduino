package com.cegeka.xparduino.state.component;

import com.cegeka.xparduino.channel.ChannelListener;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.utils.DynamicMethodInvoker;

import java.util.Collection;

public class ComponentStateEventDispatcher implements ChannelListener<Event> {

    private final DynamicMethodInvoker methodInvoker;
    private final Collection<ComponentState> componentStates;

    public ComponentStateEventDispatcher(Collection<ComponentState> componentStates) {
        this.componentStates = componentStates;
        this.methodInvoker = new DynamicMethodInvoker("on");
    }

    @Override
    public void on(Event event) {
        componentStates.stream()
                .filter(state -> stateHasPin(event, state))
                .forEach(state -> dispatchEventToState(state, event));
    }

    private boolean stateHasPin(Event event, ComponentState state) {
        return event.getPin() == state.getPin();
    }

    private void dispatchEventToState(ComponentState state, Event event) {
        methodInvoker.invoke(state, event);
        state.triggerStateChange();
    }
}
