package com.cegeka.xpdays.arduino.state.trackswitch;

import com.cegeka.xpdays.arduino.common.Direction;
import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.state.ComponentState;

public class SwitchState extends ComponentState {

    private Direction direction;

    public SwitchState(int pin) {
        super(pin);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.SWITCH;
    }
}
