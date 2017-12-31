package com.cegeka.xparduino.bootstrap.composer;

import com.cegeka.xparduino.bootstrap.ArduinoBootstrap;
import com.cegeka.xparduino.state.component.ComponentStateEventDispatcher;

public class EventDispatcherComposer implements Composer {

    @Override
    public void compose(ArduinoBootstrap bootstrap) {
        ComponentStateEventDispatcher eventDispatcher
                = new ComponentStateEventDispatcher(bootstrap.getArduinoState().getComponentStates());

        bootstrap.getEventChannel().register(eventDispatcher);
    }
}
