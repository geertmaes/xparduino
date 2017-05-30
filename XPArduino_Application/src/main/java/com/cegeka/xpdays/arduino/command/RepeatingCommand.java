package com.cegeka.xpdays.arduino.command;

import com.cegeka.xpdays.arduino.communication.CommandChannel;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unchecked")
public abstract class RepeatingCommand<T extends RepeatingCommand>
        extends AbstractCommand<RepeatingCommand> {

    private static final int DEFAULT_DELAY = 0;
    private static final int DEFAULT_PERIOD = 3;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    private final ScheduledExecutorService executorService;

    private int delay = DEFAULT_DELAY;
    private int period = DEFAULT_PERIOD;
    private TimeUnit timeUnit = DEFAULT_TIME_UNIT;

    protected RepeatingCommand(int pin, CommandChannel commandChannel, ScheduledExecutorService executorService) {
        super(pin, commandChannel);
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
    public void execute() {
        executorService.scheduleAtFixedRate(super::execute, delay, period, timeUnit);
    }
}
