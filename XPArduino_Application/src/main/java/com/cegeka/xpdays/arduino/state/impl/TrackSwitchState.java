package com.cegeka.xpdays.arduino.state.impl;

import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.event.impl.trackswitch.TrackSwitchEvent;
import com.cegeka.xpdays.arduino.model.Direction;
import com.cegeka.xpdays.arduino.state.ComponentState;

public class TrackSwitchState extends ComponentState<TrackSwitchState> {

    private Direction direction;

    public TrackSwitchState(int pin) {
        super(pin);
    }

    public void on(TrackSwitchEvent event) {
        if (event.getPin() == getPin()) {
            direction = event.getDirection();
        }
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.TRACK_SWITCH;
    }

    @Override
    public TrackSwitchState copy() {
        TrackSwitchState state = new TrackSwitchState(getPin());
        state.direction = direction;
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrackSwitchState that = (TrackSwitchState) o;

        return direction == that.direction;
    }

    @Override
    public int hashCode() {
        return direction != null ? direction.hashCode() : 0;
    }
}
