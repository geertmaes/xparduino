package com.cegeka.xparduino.channel;

public interface ChannelListener<T> {

    void on(T message);
}
