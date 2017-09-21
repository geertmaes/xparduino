package com.cegeka.xparduino.bootstrap.configurator.queue;

import com.cegeka.xparduino.bootstrap.ArduinoBootstrap;
import com.cegeka.xparduino.bootstrap.configurator.Configurator;
import com.cegeka.xparduino.queue.ArduinoQueue;

public class ArduinoQueueConfigurator implements Configurator<ArduinoQueueConfigHolder> {

    @Override
    public void configure(ArduinoQueueConfigHolder config, ArduinoBootstrap.BootstrapState state) {
        ArduinoQueue queue = initQueue(config.getArduinoQueueConfig());
        state.setArduinoQueue(queue);
    }

    private ArduinoQueue initQueue(ArduinoQueueConfig arduinoQueueConfig) {
        ArduinoQueue queue = arduinoQueueConfig.getQueue();
        queue.initialize();
        return queue;
    }

}
