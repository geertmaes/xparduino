package com.cegeka.xparduino.command.impl.trackswitch;

import com.cegeka.xparduino.command.CommandDataDeserializer;
import com.cegeka.xparduino.command.serialized.SerializedCommand;
import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.domain.Direction;

public class TrackSwitchCommandDeserializer implements CommandDataDeserializer<TrackSwitchCommand> {

    @Override
    public TrackSwitchCommand deserialize(SerializedCommand command) {
        ComponentPin pin = command.component().getPin();
        Direction direction = Direction.valueOf(command.action());
        return new TrackSwitchCommand(pin, direction);
    }
}