package com.cegeka.xparduino.queue.serialport;

import com.cegeka.xparduino.queue.ArduinoQueue;
import com.cegeka.xparduino.bootstrap.configurator.queue.ArduinoQueueConfig;

public class SerialPortQueueConfig implements ArduinoQueueConfig {

    private final ArduinoQueue messageQueue;

    public SerialPortQueueConfig(String portName) {
        this.messageQueue = new SerialPortQueue(portName);
    }

    @Override
    public ArduinoQueue getQueue() {
        return messageQueue;
    }

}
