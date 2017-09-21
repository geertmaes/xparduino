package com.cegeka.xparduino.bootstrap.composer;

import com.cegeka.xparduino.bootstrap.ArduinoBootstrap;

@FunctionalInterface
public interface Composer {

    void compose(ArduinoBootstrap bootstrap);
}
