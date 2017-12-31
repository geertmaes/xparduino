package com.cegeka.xparduino.scheduling.scheduler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SynchronousScheduler implements Scheduler {

    @Override
    public Future<?> scheduleAtFixedRate(Runnable runnable, long initialDelay, long period, TimeUnit unit) {
        runnable.run();
        return new CompletableFuture<Void>();
    }
}
