package com.cegeka.xparduino.event.impl.trackswitch;

import com.cegeka.xparduino.domain.Direction;
import com.cegeka.xparduino.event.deserialize.EventDeserializer;
import com.cegeka.xparduino.event.deserialize.EventDeserializerMapping;
import com.cegeka.xparduino.event.serialize.SerializedEvent;

@EventDeserializerMapping(TrackSwitchEvent.class)
public class TrackSwitchDeserializer implements EventDeserializer<TrackSwitchEvent> {

    @Override
    public TrackSwitchEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        Direction direction = Direction.valueOf(event.body());
        return new TrackSwitchEvent(pin, direction);
    }
}