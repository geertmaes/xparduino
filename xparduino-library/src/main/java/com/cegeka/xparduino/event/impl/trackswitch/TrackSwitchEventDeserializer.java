package com.cegeka.xparduino.event.impl.trackswitch;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.domain.Direction;
import com.cegeka.xparduino.event.EventDataDeserializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

public class TrackSwitchEventDeserializer implements EventDataDeserializer<TrackSwitchEvent> {

    @Override
    public TrackSwitchEvent deserialize(SerializedEvent event) {
        ComponentPin pin = event.component().getPin();
        Direction direction = Direction.valueOf(event.eventBody());
        return new TrackSwitchEvent(pin, direction);
    }
}