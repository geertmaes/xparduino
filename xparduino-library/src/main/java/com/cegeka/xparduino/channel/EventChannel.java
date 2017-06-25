package com.cegeka.xparduino.channel;

import com.cegeka.xparduino.event.EventListener;
import com.cegeka.xparduino.event.serialize.SerializedEvent;
import com.cegeka.xparduino.event.serialize.SerializedEventFactory;
import com.cegeka.xparduino.event.dispatch.EventDispatcher;
import com.cegeka.xparduino.queue.ArduinoQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class EventChannel implements Channel<SerializedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventChannel.class);
    private static final String BASE_PACKAGE = "com.cegeka.xparduino";

    private static volatile boolean listening = false;

    private final ArduinoQueue queue;
    private final EventDispatcher eventDispatcher;
    private final SerializedEventFactory eventFactory;

    public EventChannel(ArduinoQueue queue) {
        this.queue = queue;
        this.eventFactory = new SerializedEventFactory();
        this.eventDispatcher = EventDispatcher.scanPackage(BASE_PACKAGE);
    }

    @Override
    public void send(SerializedEvent event) {
        try {
            eventDispatcher.dispatch(event);
        } catch (Exception e) {
            LOGGER.warn("Failed to send {} dispatch event channel", event, e);
        }
    }

    @Override
    public void close() throws IOException {
        listening = false;
        queue.close();
    }

    public void listenOnQueueChanges() {
        listening = true;
        while (listening) {
            if (queue.hasNext()) {
                SerializedEvent event = eventFactory.create(queue.next());
                send(event);
            }
        }
    }

    public void registerEventListener(EventListener listener) {
        eventDispatcher.registerEventListener(listener);
    }
}
