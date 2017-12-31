package com.cegeka.xparduino.scheduling;

import com.cegeka.xparduino.scheduling.scheduler.ExecutorServiceScheduler;
import com.cegeka.xparduino.scheduling.scheduler.Scheduler;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Scheduling {

    private static final int POOL_SIZE = 10;
    private static Scheduler SCHEDULER = new ExecutorServiceScheduler(POOL_SIZE);

    public void setScheduler(Scheduler scheduler) {
        SCHEDULER = scheduler;
    }

    public static Future<?> scheduleAtFixedRate(Runnable runnable,
                                                long initialDelay,
                                                long period,
                                                TimeUnit unit) {
        return SCHEDULER.scheduleAtFixedRate(runnable, initialDelay, period, unit);
    }
}
