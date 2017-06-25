package com.cegeka.xparduino.state.component.impl;

import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.event.Handle;
import com.cegeka.xparduino.event.impl.trackswitch.TrackSwitchEvent;
import com.cegeka.xparduino.domain.Direction;
import com.cegeka.xparduino.state.component.ComponentState;

public class TrackSwitchState extends ComponentState<TrackSwitchState> {

    private Direction direction;

    public TrackSwitchState(int pin) {
        super(pin);
    }

    @Handle
    public void on(TrackSwitchEvent event) {
        direction = event.getDirection();
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
