package com.cegeka.xparduino.queue;

public class ArduinoQueueSenderImpl implements ArduinoQueueSender {

    private final ArduinoQueue queue;

    public ArduinoQueueSenderImpl(ArduinoQueue queue) {
        this.queue = queue;
    }

    @Override
    public void send(String message) {
        queue.send(message);
    }
}
