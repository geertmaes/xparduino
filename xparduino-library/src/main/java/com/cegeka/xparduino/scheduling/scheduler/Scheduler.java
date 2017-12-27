package com.cegeka.xparduino.scheduling.scheduler;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public interface Scheduler {

    Future<?> scheduleAtFixedRate(Runnable runnable, long initialDelay, long period, TimeUnit unit);
}
