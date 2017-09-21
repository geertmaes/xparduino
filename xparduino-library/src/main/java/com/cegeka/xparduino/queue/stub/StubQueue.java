package com.cegeka.xparduino.queue.stub;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.behaviour.CommandBehaviourMapper;
import com.cegeka.xparduino.command.mapper.CommandMapper;
import com.cegeka.xparduino.event.Event;
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
    private final CommandBehaviourMapper commandBehaviourMapper;

    private EventMapper eventMapper;
    private CommandMapper commandMapper;

    StubQueue() {
        eventQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY, true);
        commandBehaviourMapper = new CommandBehaviourMapper();
    }

    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    public void setCommandMapper(CommandMapper commandMapper) {
        this.commandMapper = commandMapper;
    }

    @Override
    public void initialize() {}

    @Override
    public void send(String message) {
        Command command = commandMapper.toCommand(message);
        Event event = commandBehaviourMapper.toEvent(command);

        addEventToQueue(eventMapper.toSerializedEvent(event));
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

    private void addEventToQueue(SerializedEvent event) {
        try {
            eventQueue.put(event.toString());
        } catch (InterruptedException e) {
            LOGGER.warn("Failed to add event ({}) to queue", event, e);
        }
    }

}
