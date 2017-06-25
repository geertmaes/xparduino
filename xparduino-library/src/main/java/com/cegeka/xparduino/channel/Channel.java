package com.cegeka.xparduino.channel;

import java.io.Closeable;

public interface Channel<T> extends Closeable {

    void send(T message);
}
