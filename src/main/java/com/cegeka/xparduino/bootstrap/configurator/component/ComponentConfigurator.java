package com.cegeka.xparduino.bootstrap.configurator.component;

import com.cegeka.xparduino.bootstrap.ArduinoBootstrap;
import com.cegeka.xparduino.bootstrap.configurator.Configurator;
import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.state.ArduinoState;

import java.util.Set;

public class ComponentConfigurator implements Configurator<ComponentConfigHolder> {

    @Override
    public void configure(ComponentConfigHolder config, ArduinoBootstrap.BootstrapState state) {
        Set<Component> components = config.getComponentConfig().getComponents();
        state.setArduinoState(new ArduinoState(components));
    }

}
