package com.cegeka.xparduino.event.impl.trackswitch;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.event.EventDataSerializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

import static com.cegeka.xparduino.component.ComponentType.TRACK_SWITCH;
import static com.cegeka.xparduino.event.EventCode.TRACK_SWITCH_EVENT;

public class TrackSwitchEventSerializer implements EventDataSerializer<TrackSwitchEvent> {

    @Override
    public SerializedEvent serialize(TrackSwitchEvent event) {
        String body = event.getDirection().name();
        return new SerializedEvent(TRACK_SWITCH_EVENT, body, new Component(event.getPin(), TRACK_SWITCH));
    }
}