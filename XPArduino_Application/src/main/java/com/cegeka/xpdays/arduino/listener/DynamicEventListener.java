package com.cegeka.xpdays.arduino.listener;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.dispatch.EventListener;

import java.util.function.Consumer;

public class DynamicEventListener<T extends Event> implements EventListener {

    private final Consumer<T> eventConsumer;
    private final Class<T> eventClass;

    public DynamicEventListener(Consumer<T> eventConsumer, Class<T> eventClass) {
        this.eventConsumer = eventConsumer;
        this.eventClass = eventClass;
    }

    public void on(Event event) {
        if (eventClass.isInstance(event)) {
            eventConsumer.accept((T) event);
        }
    }
}