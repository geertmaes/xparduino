package com.cegeka.xparduino.state.monitor;

import com.cegeka.xparduino.state.component.ComponentState;

import java.util.function.Function;

public class StateMonitor<S extends ComponentState<S>> {

    public static <S extends ComponentState<S>> StateMonitor<S> given(ComponentState<S> componentState) {
        return new StateMonitor<>(componentState);
    }

    private final ComponentState<S> componentState;

    private StateMonitor(ComponentState<S> componentState) {
        this.componentState = componentState;
    }

    public StateMonitorCondition<S> when(Function<S, Boolean> condition) {
        return new StateMonitorCondition<>(condition, this);
    }

    ComponentState<S> getComponentState() {
        return componentState;
    }
}
