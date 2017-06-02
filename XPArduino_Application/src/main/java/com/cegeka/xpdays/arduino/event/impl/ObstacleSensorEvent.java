package com.cegeka.xpdays.arduino.event.impl;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventDeserializer;
import com.cegeka.xpdays.arduino.event.EventMapping;
import com.cegeka.xpdays.arduino.event.dispatch.SerializedEvent;

import static com.cegeka.xpdays.arduino.event.EventCode.BASE_LED_EVENT;
import static com.cegeka.xpdays.arduino.event.EventCode.OBSTACLE_SENSOR_EVENT;

@EventMapping(value = OBSTACLE_SENSOR_EVENT, mapper = ObstacleSensorEvent.ObstacleSensorDeserializer.class)
public class ObstacleSensorEvent extends Event {

    private final boolean blocked;

    public ObstacleSensorEvent(int pin, boolean blocked) {
        super(pin);
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public static class ObstacleSensorDeserializer implements EventDeserializer<ObstacleSensorEvent> {

        @Override
        public ObstacleSensorEvent deserialize(SerializedEvent event) {
            int pin = event.getComponent().getPin();
            int signal = Integer.parseInt(event.getBody());
            return new ObstacleSensorEvent(pin, signal == 0 ? true : false);
        }
    }
}
