package com.cegeka.xparduino.state.component.monitor;

import com.cegeka.xparduino.state.component.ComponentState;

import java.util.function.Consumer;
import java.util.function.Function;

public class ComponentStateMonitorCondition<S extends ComponentState<S>> {

    private final Function<S, Boolean> condition;
    private final ComponentStateMonitor<S> stateMonitor;

    private Consumer<S> action;

    public ComponentStateMonitorCondition(Function<S, Boolean> condition,
                                          ComponentStateMonitor<S> stateMonitor) {
        this.condition = condition;
        this.stateMonitor = stateMonitor;
        registerStateListener();
    }

    public ComponentStateMonitor<S> then(Consumer<S> action) {
        this.action = action;
        return stateMonitor;
    }

    private void registerStateListener() {
        stateMonitor.getComponentState()
                .onStateChange((prev, curr) -> checkCondition(curr));
    }

    private void checkCondition(S componentState) {
        if (condition.apply(componentState) && action != null) {
            action.accept(componentState);
        }
    }
}
