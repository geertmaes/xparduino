package com.cegeka.xparduino.queue.stub;

import com.cegeka.xparduino.bootstrap.configurator.queue.ArduinoQueueConfig;
import com.cegeka.xparduino.queue.stub.emitter.EventEmitter;

import java.util.ArrayList;
import java.util.List;

public class StubQueueConfig implements ArduinoQueueConfig {

    private final StubQueue stubQueue;

    private StubQueueConfig(List<EventEmitter> eventEmitters) {
        this.stubQueue = new StubQueue(eventEmitters);
    }

    @Override
    public StubQueue getQueue() {
        return stubQueue;
    }

    public static class Builder {

        private List<EventEmitter> eventEmitters = new ArrayList<>();

        public Builder withEventEmitter(EventEmitter emitter) {
            this.eventEmitters.add(emitter);
            return this;
        }

        public StubQueueConfig build() {
            return new StubQueueConfig(eventEmitters);
        }
    }
}
