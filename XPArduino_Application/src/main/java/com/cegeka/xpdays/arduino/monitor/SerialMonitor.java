package com.cegeka.xpdays.arduino.monitor;

import java.util.function.Consumer;

public interface SerialMonitor {

    void send(String message);

    void onMessage(Consumer<String> listener);

}
