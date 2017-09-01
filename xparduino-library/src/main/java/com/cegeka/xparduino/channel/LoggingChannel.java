package com.cegeka.xparduino.channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.cegeka.xparduino.utils.ClassUtils.className;

public class LoggingChannel<T> implements Channel<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelImpl.class);

    private final Channel<T> delegate;

    public LoggingChannel(Channel<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void send(T message) {
        try {
            LOGGER.info("Sending ({}) on ({})", className(message), getIdentifier());
            delegate.send(message);
        } catch (RuntimeException e) {
            LOGGER.warn("Failed to send ({}) on ({})", className(message), getIdentifier(), e);
        }
    }

    @Override
    public void register(ChannelListener<T> listener) {
        delegate.register(listener);
    }

    @Override
    public String getIdentifier() {
        return delegate.getIdentifier();
    }

}
