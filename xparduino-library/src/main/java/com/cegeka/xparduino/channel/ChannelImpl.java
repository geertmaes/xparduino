package com.cegeka.xparduino.channel;

import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;

public class ChannelImpl<T> implements Channel<T> {

    private final String identifier;
    private final List<ChannelListener<T>> listeners = new LinkedList<>();

    public ChannelImpl(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public void send(T message) {
        try {
            listeners.forEach(listener -> listener.on(message));
        } catch (Exception e) {
            throw new RuntimeException(format("Failed to send %s on %s", message, identifier), e);
        }
    }

    @Override
    public void register(ChannelListener<T> listener) {
        listeners.add(listener);
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

}
