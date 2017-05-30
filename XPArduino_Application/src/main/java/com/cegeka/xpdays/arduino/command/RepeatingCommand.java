package com.cegeka.xpdays.arduino.command;

import com.cegeka.xpdays.arduino.component.Component;
import com.cegeka.xpdays.arduino.monitor.SerialMonitor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unchecked")
public abstract class RepeatingCommand<T extends RepeatingCommand> extends AbstractCommand {

    private static final int DEFAULT_DELAY = 0;
    private static final int DEFAULT_PERIOD = 3;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    private final ScheduledExecutorService executorService;

    private int delay = DEFAULT_DELAY;
    private int period = DEFAULT_PERIOD;
    private TimeUnit timeUnit = DEFAULT_TIME_UNIT;

    RepeatingCommand(SerialMonitor monitor, ScheduledExecutorService executorService) {
        super(monitor);
        this.executorService = executorService;
    }

    public T withDelay(int delay) {
        this.delay = delay;
        return (T) this;
    }

    public T withPeriod(int period) {
        this.period = period;
        return (T) this;
    }

    public T withTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return (T) this;
    }

    @Override
    public abstract String getAction();

    @Override
    public abstract Component getComponent();

    @Override
    public void execute() {
        executorService.scheduleAtFixedRate(super::execute, delay, period, timeUnit);
    }
}
