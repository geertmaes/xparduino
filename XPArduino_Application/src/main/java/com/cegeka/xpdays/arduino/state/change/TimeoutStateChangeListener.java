package com.cegeka.xpdays.arduino.state.change;

import com.cegeka.xpdays.arduino.state.ComponentState;

import java.util.Timer;
import java.util.TimerTask;

public class TimeoutStateChangeListener<T extends ComponentState<T>>
        implements StateChangeListener<T> {

    public static <T extends ComponentState<T>> StateChangeListener<T> onTimeout(StateChangeListener<T> delegate, int timeout) {
        return new TimeoutStateChangeListener<>(delegate, timeout);
    }

    private final StateChangeListener<T> delegate;
    private final int timeout;

    private Timer timer = null;
    private T previousState = null;

    private TimeoutStateChangeListener(StateChangeListener<T> delegate, int timeout) {
        this.delegate = delegate;
        this.timeout = timeout;
    }

    @Override
    public void onChange(T state) {
        this.previousState = state;
        if (timer == null) {
            delegate.onChange(previousState);
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    delegate.onChange(previousState);
                    timer = null;
                }
            }, timeout);
        }
    }
}
