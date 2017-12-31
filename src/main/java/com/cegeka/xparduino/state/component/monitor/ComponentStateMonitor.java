package com.cegeka.xparduino.state.component.monitor;

import com.cegeka.xparduino.state.component.ComponentState;

import java.util.function.Function;

public class ComponentStateMonitor<S extends ComponentState<S>> {

    public static <S extends ComponentState<S>> ComponentStateMonitor<S> given(ComponentState<S> componentState) {
        return new ComponentStateMonitor<>(componentState);
    }

    private final ComponentState<S> componentState;

    private ComponentStateMonitor(ComponentState<S> componentState) {
        this.componentState = componentState;
    }

    public ComponentStateMonitorCondition<S> when(Function<S, Boolean> condition) {
        return new ComponentStateMonitorCondition<>(condition, this);
    }

    ComponentState<S> getComponentState() {
        return componentState;
    }
}
