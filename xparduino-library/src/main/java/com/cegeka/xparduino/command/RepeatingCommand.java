package com.cegeka.xparduino.command;

import com.cegeka.xparduino.channel.Channel;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unchecked")
public abstract class RepeatingCommand<T extends RepeatingCommand> extends AbstractCommand {

    private static final int DEFAULT_DELAY = 0;
    private static final int DEFAULT_PERIOD = 3;
    private static final int DEFAULT_TIMES = 0;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    private final ScheduledExecutorService executorService;

    private int delay = DEFAULT_DELAY;
    private int period = DEFAULT_PERIOD;
    private int times = DEFAULT_TIMES;
    private TimeUnit timeUnit = DEFAULT_TIME_UNIT;
    private ScheduledFuture<?> scheduledFuture;
    private int counter = 0;

    protected RepeatingCommand(int pin,
                               Channel<Command> commandChannel,
                               ScheduledExecutorService executorService) {
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

    public T withTimes(int times) {
        this.times = times;
        return (T) this;
    }

    public T withTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return (T) this;
    }

    public void stop(){
        scheduledFuture.cancel(true);
    }

    @Override
    public void execute() {
        scheduledFuture = executorService.scheduleAtFixedRate(this::executeCommand, delay, period, timeUnit);
    }

    private void executeCommand(){
        super.execute();
        counter++;
        if(times > 0 && counter > times){
            stop();
            counter = 0;
        }
    }
}
