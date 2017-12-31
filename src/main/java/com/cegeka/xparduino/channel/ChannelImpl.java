package com.cegeka.xparduino.channel;

import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;

public class ChannelImpl<T> implements Channel<T> {

    private final List<ChannelListener<T>> listeners = new LinkedList<>();

    @Override
    public void send(T message) {
        try {
            listeners.forEach(listener -> listener.on(message));
        } catch (Exception e) {
            throw new RuntimeException(format("Failed to send (%s)", message), e);
        }
    }

    @Override
    public void register(ChannelListener<T> listener) {
        listeners.add(listener);
    }

}
