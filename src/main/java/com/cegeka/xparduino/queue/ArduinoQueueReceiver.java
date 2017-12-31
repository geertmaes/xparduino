package com.cegeka.xparduino.queue;

public interface ArduinoQueueReceiver {

    void onMessage(String message);
}
