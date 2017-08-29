package com.cegeka.xparduino.state.component.impl;

import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.event.impl.obstaclesensor.ObstacleSensorEvent;
import com.cegeka.xparduino.state.component.ComponentState;

public class ObstacleSensorState extends ComponentState<ObstacleSensorState> {

    private boolean blocked;

    public ObstacleSensorState(int pin) {
        super(pin);
    }

    public void on(ObstacleSensorEvent event) {
        blocked = event.isBlocked();
    }

    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.OBSTACLE_SENSOR;
    }

    @Override
    public ObstacleSensorState copy() {
        ObstacleSensorState state = new ObstacleSensorState(getPin());
        state.blocked = blocked;
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ObstacleSensorState that = (ObstacleSensorState) o;

        return blocked == that.blocked;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (blocked ? 1 : 0);
        return result;
    }
}
