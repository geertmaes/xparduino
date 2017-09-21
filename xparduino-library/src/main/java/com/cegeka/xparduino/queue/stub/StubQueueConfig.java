package com.cegeka.xparduino.queue.stub;

import com.cegeka.xparduino.bootstrap.configurator.queue.ArduinoQueueConfig;

public class StubQueueConfig implements ArduinoQueueConfig {

    private final StubQueue stubQueue;

    public StubQueueConfig() {
        stubQueue = new StubQueue();
    }

    @Override
    public StubQueue getQueue() {
        return stubQueue;
    }
}
