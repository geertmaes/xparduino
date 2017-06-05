package com.cegeka.xpdays.arduino.event.impl.obstaclesensor;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventMapping;

import static com.cegeka.xpdays.arduino.event.EventCode.OBSTACLE_SENSOR_EVENT;

@EventMapping(OBSTACLE_SENSOR_EVENT)
public class ObstacleSensorEvent implements Event {

    private final int pin;
    private final boolean blocked;

    public ObstacleSensorEvent(int pin, boolean blocked) {
        this.pin = pin;
        this.blocked = blocked;
    }

    @Override
    public int getPin() {
        return pin;
    }

    public boolean isBlocked() {
        return blocked;
    }
}
