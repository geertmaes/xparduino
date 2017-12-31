package com.cegeka.xparduino.command;

import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.scheduling.Scheduling;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unchecked")
public class RepeatingCommandImpl<T extends Command>
        extends ExecutableCommandImpl<T>
        implements RepeatingCommand<T> {

    private static final int DEFAULT_DELAY = 0;
    private static final int DEFAULT_PERIOD = 3;
    private static final int DEFAULT_TIMES = 0;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    public static <T extends Command> RepeatingCommand<T> repeating(T delegate,
                                                                    Channel<Command> commandChannel) {
        return new RepeatingCommandImpl<>(delegate, commandChannel);
    }

    private int delay = DEFAULT_DELAY;
    private int period = DEFAULT_PERIOD;
    private int times = DEFAULT_TIMES;
    private TimeUnit timeUnit = DEFAULT_TIME_UNIT;

    private int counter = 0;
    private Future<?> scheduledFuture;

    private RepeatingCommandImpl(T delegate,
                                 Channel<Command> commandChannel) {
        super(delegate, commandChannel);
    }

    @Override
    public RepeatingCommand<T> withDelay(int delay) {
        this.delay = delay;
        return this;
    }

    @Override
    public RepeatingCommand<T> withPeriod(int period) {
        this.period = period;
        return this;
    }

    @Override
    public RepeatingCommand<T> withTimes(int times) {
        this.times = times;
        return this;
    }

    @Override
    public RepeatingCommand<T> withTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }

    @Override
    public void stop() {
        scheduledFuture.cancel(true);
    }

    @Override
    public void execute() {
        scheduledFuture = Scheduling.scheduleAtFixedRate(this::executeCommand, delay, period, timeUnit);
    }

    private void executeCommand() {
        super.execute();
        counter++;
        if (times > 0 && counter > times) {
            stop();
            counter = 0;
        }
    }
}
