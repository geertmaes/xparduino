package com.cegeka.xparduino.queue.stub;

import com.cegeka.xparduino.componentaction.flow.ComponentActionFlow;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.mapper.EventMapper;
import com.cegeka.xparduino.event.serialized.SerializedEvent;
import com.cegeka.xparduino.queue.ArduinoQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

public class StubQueue implements ArduinoQueue {

    private static final Logger LOGGER = LoggerFactory.getLogger(StubQueue.class);

    private final ComponentActionFlow componentActionFlow = new ComponentActionFlow();
    private final BlockingQueue<String> nextEvents = new ArrayBlockingQueue<>(1024, true);

    private EventMapper eventMapper;

    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @Override
    public void initialize() {
        LOGGER.info("Initializing stub queue");
    }

    @Override
    public void send(String message) {
        LOGGER.info("Received command ({}) on stub queue", message);
        handle(message);
    }

    private void handle(String message) {
        handleEvents(componentActionFlow.handle(message));
    }

    private void handleEvents(Stream<Event> events) {
        events.map(eventMapper::mapToSerializedEvent)
                .map(SerializedEvent::toString)
                .forEach(this::addEventToQueue);
    }

    private void addEventToQueue(String event) {
        try {
            nextEvents.put(event);
        } catch (InterruptedException e) {
            LOGGER.warn("Failed to add event to queue", e);
        }
    }

    @Override
    public String next() {
        return nextEvents.poll();
    }

    @Override
    public boolean hasNext() {
        return nextEvents.peek() != null;
    }

    @Override
    public void close() throws IOException {
        LOGGER.info("Closing stub queue");
    }

}
