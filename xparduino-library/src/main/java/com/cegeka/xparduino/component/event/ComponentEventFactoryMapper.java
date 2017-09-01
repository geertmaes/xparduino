package com.cegeka.xparduino.component.event;

import com.cegeka.xparduino.component.ComponentType;

public class ComponentEventFactoryMapper {

    public ComponentEventFactory toFactory(ComponentType type) {
        switch (type) {
            case BASE_LED:
                return new BaseLedEventFactory();
            default:
                return new NoopEventFactory();
        }
    }

}
