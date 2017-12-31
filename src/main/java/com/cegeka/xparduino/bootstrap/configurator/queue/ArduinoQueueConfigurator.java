package com.cegeka.xparduino.bootstrap.configurator.queue;

import com.cegeka.xparduino.bootstrap.ArduinoBootstrap;
import com.cegeka.xparduino.bootstrap.configurator.Configurator;

public class ArduinoQueueConfigurator implements Configurator<ArduinoQueueConfigHolder> {

    @Override
    public void configure(ArduinoQueueConfigHolder config, ArduinoBootstrap.BootstrapState state) {
        ArduinoQueueConfig queueConfig = config.getArduinoQueueConfig();
        state.setArduinoQueue(queueConfig.getQueue());
    }
}
