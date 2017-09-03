package com.cegeka.xparduino.component.event;

import com.cegeka.xparduino.component.ComponentType;

public class ComponentEventFactoryMapper {

    public ComponentEventFactory toFactory(ComponentType type) {
        switch (type) {
            case BASE_LED:
                return new BaseLedEventFactory();
            case TRACK_SWITCH:
                return new TrackSwitchEventFactory();
            default:
                throw new RuntimeException(String.format("No event factory implementation for %s", type));
        }
    }

}
