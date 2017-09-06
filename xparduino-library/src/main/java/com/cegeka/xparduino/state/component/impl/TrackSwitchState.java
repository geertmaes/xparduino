package com.cegeka.xparduino.state.component.impl;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.domain.Direction;
import com.cegeka.xparduino.event.impl.trackswitch.TrackSwitchEvent;
import com.cegeka.xparduino.state.component.ComponentState;

public class TrackSwitchState extends ComponentState<TrackSwitchState> {

    private Direction direction;

    public TrackSwitchState(ComponentPin pin) {
        super(pin);
    }

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
        if (!super.equals(o)) return false;

        TrackSwitchState that = (TrackSwitchState) o;

        return direction == that.direction;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }
}
