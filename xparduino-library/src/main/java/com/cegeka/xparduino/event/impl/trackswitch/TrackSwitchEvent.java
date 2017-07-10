package com.cegeka.xparduino.event.impl.trackswitch;

import com.cegeka.xparduino.domain.Direction;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventMapping;

import static com.cegeka.xparduino.event.EventCode.TRACK_SWITCH_EVENT;

@EventMapping(
        code = TRACK_SWITCH_EVENT,
        serializer = TrackSwitchEventSerializer.class,
        deserializer = TrackSwitchEventDeserializer.class)
public class TrackSwitchEvent implements Event {

    private final int pin;
    private final Direction direction;

    public TrackSwitchEvent(int pin, Direction direction) {
        this.pin = pin;
        this.direction = direction;
    }

    @Override
    public int getPin() {
        return pin;
    }

    public Direction getDirection() {
        return direction;
    }
}
