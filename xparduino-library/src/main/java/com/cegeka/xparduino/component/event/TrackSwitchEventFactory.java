package com.cegeka.xparduino.component.event;

import com.cegeka.xparduino.command.serialized.SerializedCommand;
import com.cegeka.xparduino.domain.Direction;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.impl.trackswitch.TrackSwitchEvent;

import java.util.stream.Stream;

public class TrackSwitchEventFactory implements ComponentEventFactory {

    private static final String LEFT_ACTION = "left";
    private static final String RIGHT_ACTION = "right";

    @Override
    public Stream<Event> create(SerializedCommand command) {
        String action = command.action();
        if (isLeftAction(action))
            return Stream.of(new TrackSwitchEvent(command.pin(), Direction.LEFT));
        if (isRightAction(action))
            return Stream.of(new TrackSwitchEvent(command.pin(), Direction.RIGHT));
        return Stream.empty();
    }

    private boolean isLeftAction(String action) {
        return action.equalsIgnoreCase(LEFT_ACTION);
    }

    private boolean isRightAction(String action) {
        return action.equalsIgnoreCase(RIGHT_ACTION);
    }

}
