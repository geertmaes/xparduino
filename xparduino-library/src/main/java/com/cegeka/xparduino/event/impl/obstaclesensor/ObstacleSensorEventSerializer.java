package com.cegeka.xparduino.event.impl.obstaclesensor;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.event.EventSerializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

import static com.cegeka.xparduino.component.ComponentType.OBSTACLE_SENSOR;
import static com.cegeka.xparduino.event.EventCode.OBSTACLE_SENSOR_EVENT;

public class ObstacleSensorEventSerializer implements EventSerializer<ObstacleSensorEvent> {

    @Override
    public SerializedEvent serialize(ObstacleSensorEvent event) {
        String body = event.isBlocked() ? "0": "1";
        return new SerializedEvent(OBSTACLE_SENSOR_EVENT, body, new Component(event.getPin(), OBSTACLE_SENSOR));
    }
}