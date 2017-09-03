package com.cegeka.xparduino.channel;

public class NamedChannel<T> implements Channel<T> {

    private final String identifier;
    private final Channel<T> delegate;

    public NamedChannel(Channel<T> delegate, String identifier) {
        this.delegate = delegate;
        this.identifier = identifier;
    }

    @Override
    public void send(T message) {
        delegate.send(message);
    }

    @Override
    public void register(ChannelListener<T> listener) {
        delegate.register(listener);
    }

    String getIdentifier() {
        return identifier;
    }

}
