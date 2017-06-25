package com.cegeka.xparduino.event.impl.trackswitch;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventCode;
import com.cegeka.xparduino.event.EventMapping;
import com.cegeka.xparduino.domain.Direction;

@EventMapping(EventCode.TRACK_SWITCH_EVENT)
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
