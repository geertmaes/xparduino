package com.cegeka.xparduino.utils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newScheduledThreadPool;

public class Scheduling {

    private static final int POOL_SIZE = 10;
    private static final ScheduledExecutorService executorService;

    static {
        executorService = newScheduledThreadPool(POOL_SIZE, new NamedThreadFactory());
    }

    public static void scheduleAtFixedRate(Runnable runnable,
                                           long initialDelay,
                                           long period,
                                           TimeUnit unit) {
        executorService.scheduleAtFixedRate(runnable, initialDelay, period, unit);
    }
}
