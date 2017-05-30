package com.cegeka.xpdays.arduino.monitor;

import java.io.Closeable;
import java.util.function.Consumer;

public interface SerialMonitor extends Closeable{

    void send(String message);

    void onMessage(Consumer<String> listener);

}
