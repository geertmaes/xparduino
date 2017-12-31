package com.cegeka.xparduino.channel;

public interface Channel<T> {

    void send(T message);

    void register(ChannelListener<T> listener);
}
