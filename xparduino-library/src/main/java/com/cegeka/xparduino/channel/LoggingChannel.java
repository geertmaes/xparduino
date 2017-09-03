package com.cegeka.xparduino.channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.cegeka.xparduino.utils.ClassUtils.className;

public class LoggingChannel<T> implements Channel<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelImpl.class);

    private final NamedChannel<T> delegate;

    public LoggingChannel(NamedChannel<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void send(T message) {
        try {
            LOGGER.info("Sending {} on {}", className(message), delegate.getIdentifier());
            delegate.send(message);
        } catch (RuntimeException e) {
            LOGGER.warn("Failed to send {} on {}", className(message), delegate.getIdentifier(), e);
        }
    }

    @Override
    public void register(ChannelListener<T> listener) {
        delegate.register(listener);
    }

}
