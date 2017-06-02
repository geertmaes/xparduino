package com.cegeka.xpdays.arduino.state.obstaclesensor;

import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.state.ComponentState;

public class ObstacleSensorState extends ComponentState {

    private boolean blocked;

    public ObstacleSensorState(int pin) {
        super(pin);
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.OBSTACLE_SENSOR;
    }
}
