package com.cegeka.xparduino.command;

import java.util.concurrent.TimeUnit;

public interface RepeatingCommand<T extends Command> extends ExecutableCommand<T> {

    RepeatingCommand<T> withTimes(int times);

    RepeatingCommand<T> withDelay(int delay);

    RepeatingCommand<T> withPeriod(int period);

    RepeatingCommand<T> withTimeUnit(TimeUnit timeUnit);

    void stop();
}
