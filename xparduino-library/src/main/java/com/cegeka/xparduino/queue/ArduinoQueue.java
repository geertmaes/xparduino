package com.cegeka.xparduino.queue;

import java.io.Closeable;

public interface ArduinoQueue extends Closeable {

    void initialize();

    void send(String message);

    String next();

    boolean hasNext();
}
