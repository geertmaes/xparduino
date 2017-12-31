package com.cegeka.xparduino.bootstrap.configurator;

import com.cegeka.xparduino.bootstrap.ArduinoBootstrap;

@FunctionalInterface
public interface Configurator<T> {

    void configure(T config, ArduinoBootstrap.BootstrapState bootstrap);
}
