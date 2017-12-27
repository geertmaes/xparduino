package com.cegeka.xparduino.state.monitor;

import com.cegeka.xparduino.state.component.ComponentState;

import java.util.function.Consumer;
import java.util.function.Function;

public class StateMonitorCondition<S extends ComponentState<S>> {

    private final Function<S, Boolean> condition;
    private final StateMonitor<S> stateMonitor;

    private Consumer<S> action;

    public StateMonitorCondition(Function<S, Boolean> condition,
                                 StateMonitor<S> stateMonitor) {
        this.condition = condition;
        this.stateMonitor = stateMonitor;
        registerStateListener();
    }

    public StateMonitor<S> then(Consumer<S> action) {
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
