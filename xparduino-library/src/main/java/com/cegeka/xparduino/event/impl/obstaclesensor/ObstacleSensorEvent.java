package com.cegeka.xparduino.event.impl.obstaclesensor;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventMapping;

import static com.cegeka.xparduino.event.EventCode.OBSTACLE_SENSOR_EVENT;

@EventMapping(
        code = OBSTACLE_SENSOR_EVENT,
        serializer = ObstacleSensorEventSerializer.class,
        deserializer = ObstacleSensorEventDeserializer.class)
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
