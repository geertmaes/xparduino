package com.cegeka.xparduino.event.impl.trackswitch;

import com.cegeka.xparduino.domain.Direction;
import com.cegeka.xparduino.event.EventDeserializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

public class TrackSwitchEventDeserializer implements EventDeserializer<TrackSwitchEvent> {

    @Override
    public TrackSwitchEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        Direction direction = Direction.valueOf(event.eventBody());
        return new TrackSwitchEvent(pin, direction);
    }
}