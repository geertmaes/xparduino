package com.cegeka.xpdays.arduino.listener;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.dispatch.EventListener;

import java.util.function.Consumer;

public class DynamicEventListener<T extends Event> implements EventListener {

    private final Consumer<T> eventConsumer;

    public DynamicEventListener(Consumer<T> eventConsumer) {
        this.eventConsumer = eventConsumer;
    }

    public void on(T event) {
        eventConsumer.accept(event);
    }
}