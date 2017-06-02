package com.cegeka.xpdays.arduino.event.impl;

import com.cegeka.xpdays.arduino.common.Direction;
import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventDeserializer;
import com.cegeka.xpdays.arduino.event.EventMapping;
import com.cegeka.xpdays.arduino.event.dispatch.SerializedEvent;

import static com.cegeka.xpdays.arduino.event.EventCode.SWITCH_EVENT;

@EventMapping(value = SWITCH_EVENT, mapper = SwitchEvent.SwitchDeserializer.class)
public class SwitchEvent extends Event {

    private final Direction direction;

    public SwitchEvent(int pin, Direction direction) {
        super(pin);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public static class SwitchDeserializer implements EventDeserializer<SwitchEvent> {

        @Override
        public SwitchEvent deserialize(SerializedEvent event) {
            int pin = event.getComponent().getPin();
            Direction direction = Direction.valueOf(event.getBody());
            return new SwitchEvent(pin, direction);
        }
    }
}
