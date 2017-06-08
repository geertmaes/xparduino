package com.cegeka.xpdays.arduino.state.impl;

import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.event.Handle;
import com.cegeka.xpdays.arduino.event.impl.obstaclesensor.ObstacleSensorEvent;
import com.cegeka.xpdays.arduino.state.ComponentState;

public class ObstacleSensorState extends ComponentState<ObstacleSensorState> {

    private boolean blocked;

    public ObstacleSensorState(int pin) {
        super(pin);
    }

    @Handle
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

        ObstacleSensorState that = (ObstacleSensorState) o;

        return blocked == that.blocked;
    }

    @Override
    public int hashCode() {
        return (blocked ? 1 : 0);
    }
}
