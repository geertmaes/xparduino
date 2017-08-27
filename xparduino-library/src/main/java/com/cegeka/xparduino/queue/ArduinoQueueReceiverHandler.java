package com.cegeka.xparduino.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ArduinoQueueReceiverHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoQueueReceiverHandler.class);
    private static final int LISTEN_PERIOD = 100;
    private static final int LISTEN_DELAY = 1000;

    private final ArduinoQueue queue;
    private final List<ArduinoQueueReceiver> queueReceivers;
    private final ScheduledExecutorService executorService;

    public ArduinoQueueReceiverHandler(ArduinoQueue queue, List<ArduinoQueueReceiver> queueReceivers) {
        this.queue = queue;
        this.queueReceivers = queueReceivers;
        this.executorService = Executors.newScheduledThreadPool(4);
    }

    public void listenOnQueueChanges() {
        executorService.scheduleAtFixedRate(this::handleQueueEvent, LISTEN_DELAY, LISTEN_PERIOD, MILLISECONDS);
    }

    private void handleQueueEvent() {
        if (queue.hasNext()) {
            String message = queue.next();
            LOGGER.info("Received message ({}) from queue", message);
            queueReceivers.forEach(receiver -> receiver.onMessage(message));
        }
    }
}