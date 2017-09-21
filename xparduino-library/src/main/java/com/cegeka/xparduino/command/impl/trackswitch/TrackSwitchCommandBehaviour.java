package com.cegeka.xparduino.command.impl.trackswitch;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.behaviour.CommandBehaviour;
import com.cegeka.xparduino.event.impl.trackswitch.TrackSwitchEvent;

public class TrackSwitchCommandBehaviour implements CommandBehaviour<TrackSwitchCommand, TrackSwitchEvent> {

    @Override
    public TrackSwitchEvent create(TrackSwitchCommand command) {
        return new TrackSwitchEvent(command.pin(), command.getDirection());
    }

    @Override
    public boolean canHandle(Command command) {
        return TrackSwitchCommand.class.isInstance(command);
    }

}
