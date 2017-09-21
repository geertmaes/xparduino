package com.cegeka.xparduino;

import com.cegeka.xparduino.bootstrap.ArduinoBootstrap;
import com.cegeka.xparduino.bootstrap.ArduinoConfiguration;

public class ArduinoFactory {

    public Arduino create(ArduinoConfiguration configuration) {
        return ArduinoBootstrap.fromConfiguration(configuration);
    }
}
