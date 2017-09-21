package com.cegeka.xparduino.queue;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ArduinoQueueReceiverHandler {

    private static final int POOL_SIZE = 4;
    private static final int LISTEN_PERIOD = 10;
    private static final int LISTEN_DELAY = 0;

    private final ArduinoQueue queue;
    private final List<ArduinoQueueReceiver> queueReceivers;
    private final ScheduledExecutorService executorService;

    public ArduinoQueueReceiverHandler(ArduinoQueue queue, List<ArduinoQueueReceiver> queueReceivers) {
        this.queue = queue;
        this.queueReceivers = queueReceivers;
        this.executorService = Executors.newScheduledThreadPool(POOL_SIZE);
    }

    public void listenOnQueueChanges() {
        executorService.scheduleAtFixedRate(this::handleQueueEvent, LISTEN_DELAY, LISTEN_PERIOD, MILLISECONDS);
    }

    private void handleQueueEvent() {
        if (queue.hasNext()) {
            String message = queue.next();
            queueReceivers.forEach(receiver -> receiver.onMessage(message));
        }
    }
}