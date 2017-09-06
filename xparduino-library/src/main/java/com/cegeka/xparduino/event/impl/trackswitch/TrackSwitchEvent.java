package com.cegeka.xparduino.event.impl.trackswitch;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.domain.Direction;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventMapping;

import static com.cegeka.xparduino.event.EventCode.TRACK_SWITCH_EVENT;

@EventMapping(
        code = TRACK_SWITCH_EVENT,
        serializer = TrackSwitchEventSerializer.class,
        deserializer = TrackSwitchEventDeserializer.class)
public class TrackSwitchEvent implements Event {

    private final ComponentPin pin;
    private final Direction direction;

    public TrackSwitchEvent(ComponentPin pin, Direction direction) {
        this.pin = pin;
        this.direction = direction;
    }

    @Override
    public ComponentPin getPin() {
        return pin;
    }

    public Direction getDirection() {
        return direction;
    }
}
