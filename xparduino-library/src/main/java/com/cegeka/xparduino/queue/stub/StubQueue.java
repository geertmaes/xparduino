package com.cegeka.xparduino.queue.stub;

import com.cegeka.xparduino.queue.ArduinoQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.BiConsumer;

public class StubQueue implements ArduinoQueue {

    private static final Logger LOGGER = LoggerFactory.getLogger(StubQueue.class);

    private volatile String nextEvent;
    private volatile BiConsumer<String, StubQueue> onCommandCallback;

    StubQueue() {}

    @Override
    public void send(String message) {
        LOGGER.info("Sending message ({}) on stub queue", message);
        onCommandCallback.accept(message, this);
    }

    @Override
    public String next() {
        String event = nextEvent;
        nextEvent = null;
        return event;
    }

    @Override
    public boolean hasNext() {
        return nextEvent != null;
    }

    @Override
    public void close() throws IOException {
        LOGGER.info("Closing stub queue");
    }

    public void setNextEvent(String nextEvent) {
        this.nextEvent = nextEvent;
    }

    public void onCommandCallback(BiConsumer<String, StubQueue> onCommandCallback) {
        this.onCommandCallback = onCommandCallback;
    }
}
