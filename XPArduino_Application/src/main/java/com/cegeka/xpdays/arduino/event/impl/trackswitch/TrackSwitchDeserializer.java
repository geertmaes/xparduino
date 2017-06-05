package com.cegeka.xpdays.arduino.event.impl.trackswitch;

import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializer;
import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializerMapping;
import com.cegeka.xpdays.arduino.event.dispatch.SerializedEvent;
import com.cegeka.xpdays.arduino.model.Direction;

@EventDeserializerMapping(TrackSwitchEvent.class)
public class TrackSwitchDeserializer implements EventDeserializer<TrackSwitchEvent> {

    @Override
    public TrackSwitchEvent deserialize(SerializedEvent event) {
        int pin = event.component().getPin();
        Direction direction = Direction.valueOf(event.body());
        return new TrackSwitchEvent(pin, direction);
    }
}