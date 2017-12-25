package com.cegeka.xparduino.queue.stub;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.behaviour.CommandBehaviourMapper;
import com.cegeka.xparduino.command.mapper.CommandMapper;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.mapper.EventMapper;
import com.cegeka.xparduino.event.serialized.SerializedEvent;
import com.cegeka.xparduino.queue.ArduinoQueue;
import com.cegeka.xparduino.queue.stub.emitter.EventEmitter;
import com.cegeka.xparduino.queue.stub.emitter.EventEmitterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.cegeka.xparduino.utils.Scheduling.scheduleAtFixedRate;

public class StubQueue implements ArduinoQueue {

    private static final Logger LOGGER = LoggerFactory.getLogger(StubQueue.class);

    private static final int QUEUE_CAPACITY = 1024;

    private final BlockingQueue<String> eventQueue;
    private final List<EventEmitter> eventEmitters;
    private final CommandBehaviourMapper commandBehaviourMapper;

    private EventMapper eventMapper;
    private CommandMapper commandMapper;

    StubQueue(List<EventEmitter> eventEmitters) {
        this.eventEmitters = eventEmitters;
        this.eventQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY, true);
        this.commandBehaviourMapper = new CommandBehaviourMapper();
    }

    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    public void setCommandMapper(CommandMapper commandMapper) {
        this.commandMapper = commandMapper;
    }

    @Override
    public void initialize() {
        eventEmitters.forEach(this::initializeEventEmitter);
    }

    @Override
    public void send(String message) {
        Command command = commandMapper.toCommand(message);
        Event event = commandBehaviourMapper.toEvent(command);
        addEventToQueue(event);
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

    private void initializeEventEmitter(EventEmitter eventEmitter) {
        EventEmitterConfig emitterConfig = eventEmitter.getEmitterConfig();
        scheduleAtFixedRate(
                () -> addEventToQueue(eventEmitter.nextEvent()),
                emitterConfig.getInitialDelay(),
                emitterConfig.getPeriod(),
                emitterConfig.getTimeUnit());
    }

    private void addEventToQueue(Event event) {
        SerializedEvent serializedEvent = eventMapper.toSerializedEvent(event);
        addSerializedEventToQueue(serializedEvent);
    }

    private void addSerializedEventToQueue(SerializedEvent event) {
        try {
            eventQueue.put(event.toString());
        } catch (InterruptedException e) {
            LOGGER.warn("Failed to add event ({}) to queue", event, e);
        }
    }
}
