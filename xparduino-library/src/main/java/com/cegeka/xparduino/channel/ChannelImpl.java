package com.cegeka.xparduino.channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class ChannelImpl<T> implements Channel<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelImpl.class);

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
            LOGGER.warn("Failed to send ({}) over channel ({})", message, identifier, e);
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
