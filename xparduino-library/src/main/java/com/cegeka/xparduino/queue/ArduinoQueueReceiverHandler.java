package com.cegeka.xparduino.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArduinoQueueReceiverHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoQueueReceiverHandler.class);

    private final ArduinoQueue queue;
    private final List<ArduinoQueueReceiver> queueReceivers;
    private final ExecutorService executorService;

    public ArduinoQueueReceiverHandler(ArduinoQueue queue, List<ArduinoQueueReceiver> queueReceivers) {
        this.queue = queue;
        this.queueReceivers = queueReceivers;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void listenOnQueueChanges() {
        executorService.submit(() -> {
            while (true) {
                if (queue.hasNext()) {
                    String message = queue.next();
                    LOGGER.info("Received message ({}) from queue", message);
                    queueReceivers.forEach(receiver -> receiver.onMessage(message));
                }
            }
        });
    }
}