package com.cegeka.xparduino.queue.stub;

import com.cegeka.xparduino.queue.ArduinoQueueConfiguration;

public class StubQueueConfiguration implements ArduinoQueueConfiguration {

    private final StubQueue stubQueue;

    public StubQueueConfiguration() {
        stubQueue = new StubQueue();
    }

    @Override
    public StubQueue getQueue() {
        return stubQueue;
    }
}
