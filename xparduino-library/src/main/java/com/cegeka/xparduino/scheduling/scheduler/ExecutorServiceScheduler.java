package com.cegeka.xparduino.scheduling.scheduler;

import com.cegeka.xparduino.scheduling.NamedThreadFactory;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newScheduledThreadPool;

public class ExecutorServiceScheduler implements Scheduler {

    private final ScheduledExecutorService executorService;

    public ExecutorServiceScheduler(int poolSize) {
        this.executorService = newScheduledThreadPool(poolSize, new NamedThreadFactory());
    }

    @Override
    public Future<?> scheduleAtFixedRate(Runnable runnable, long initialDelay, long period, TimeUnit unit) {
        return executorService.scheduleAtFixedRate(runnable, initialDelay, period, unit);
    }
}
