package com.cegeka.xparduino.scheduling;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {

    private final AtomicInteger currentThreadCount = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, String.format("xparduino-%d", currentThreadCount.getAndIncrement()));
    }
}
