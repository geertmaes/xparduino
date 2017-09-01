package com.cegeka.xparduino.queue.stub;

import com.cegeka.xparduino.event.mapper.EventMapper;
import com.cegeka.xparduino.event.serialized.SerializedEvent;
import com.cegeka.xparduino.queue.ArduinoQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class StubQueue implements ArduinoQueue {

    private static final Logger LOGGER = LoggerFactory.getLogger(StubQueue.class);
    private static final int QUEUE_CAPACITY = 1024;

    private final BlockingQueue<String> eventQueue;
    private final StubQueueMessageHandler messageHandler;

    private EventMapper eventMapper;

    StubQueue() {
        messageHandler = new StubQueueMessageHandler();
        eventQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY, true);
    }

    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @Override
    public void initialize() {}

    @Override
    public void send(String message) {
        messageHandler.handle(message)
                .map(eventMapper::mapToSerializedEvent)
                .map(SerializedEvent::toString)
                .forEach(this::addEventToQueue);
    }

    @Override
    public String next() {
        return eventQueue.poll();
    }

    @Override
    public boolean hasNext() {
        return eventQueue.peek() != null;
    }

    @Override
    public void close() throws IOException {
        LOGGER.info("Closing stub queue");
    }

    private void addEventToQueue(String event) {
        try {
            eventQueue.put(event);
        } catch (InterruptedException e) {
            LOGGER.warn("Failed to add event ({}) to queue", event, e);
        }
    }

}
