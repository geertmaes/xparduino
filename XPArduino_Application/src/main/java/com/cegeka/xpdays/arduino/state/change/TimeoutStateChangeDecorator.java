package com.cegeka.xpdays.arduino.state.change;

import com.cegeka.xpdays.arduino.state.ComponentState;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeoutStateChangeDecorator<T extends ComponentState<T>>
        extends StateChangeDecorator<T> {

    public static <T extends ComponentState<T>> StateChangeListener<T> withTimeout(StateChangeListener<T> delegate, int timeout) {
        return new TimeoutStateChangeDecorator<>(delegate, timeout);
    }

    private final int timeout;
    private volatile boolean inProgress = false;
    private final ScheduledExecutorService executorService;

    private TimeoutStateChangeDecorator(StateChangeListener<T> delegate, int timeout) {
        super(delegate);
        this.timeout = timeout;
        this.executorService = Executors.newScheduledThreadPool(1);
    }

    @Override
    public void onChange(T state) {
        if (!inProgress) {
            inProgress = true;
            delegate().onChange(state);
            executorService.schedule(() -> inProgress = false, timeout, TimeUnit.MILLISECONDS);
        }
    }
}
