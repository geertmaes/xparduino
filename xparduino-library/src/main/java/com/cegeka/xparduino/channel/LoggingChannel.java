package com.cegeka.xparduino.channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

import static com.cegeka.xparduino.utils.ClassUtils.className;

public class LoggingChannel<T> implements Channel<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelImpl.class);

    public static <T> LoggingChannel<T> logged(NamedChannel<T> delegate, Function<T, String> messageConverter) {
        return new LoggingChannel<>(delegate, messageConverter);
    }

    private final NamedChannel<T> delegate;
    private final Function<T, String> messageConverter;

    private LoggingChannel(NamedChannel<T> delegate, Function<T, String> messageConverter) {
        this.delegate = delegate;
        this.messageConverter = messageConverter;
    }

    @Override
    public void send(T message) {
        try {
            LOGGER.debug("Sending {} on {}", messageConverter.apply(message), delegate.getIdentifier());
            delegate.send(message);
        } catch (RuntimeException e) {
            LOGGER.warn("Failed to send {} on {}", messageConverter.apply(message), delegate.getIdentifier(), e);
        }
    }

    @Override
    public void register(ChannelListener<T> listener) {
        delegate.register(listener);
    }

}
