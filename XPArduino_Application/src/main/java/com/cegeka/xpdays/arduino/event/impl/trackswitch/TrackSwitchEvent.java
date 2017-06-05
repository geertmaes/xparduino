package com.cegeka.xpdays.arduino.event.impl.trackswitch;

import com.cegeka.xpdays.arduino.model.Direction;
import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventMapping;

import static com.cegeka.xpdays.arduino.event.EventCode.TRACK_SWITCH_EVENT;

@EventMapping(TRACK_SWITCH_EVENT)
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
