package com.cegeka.xparduino.command.impl.trackswitch;

import com.cegeka.xparduino.command.CommandDataDeserializer;
import com.cegeka.xparduino.command.mapper.deserializer.CommandDeserializationException;
import com.cegeka.xparduino.command.serialized.SerializedCommand;
import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.domain.Direction;

public class TrackSwitchCommandDeserializer implements CommandDataDeserializer<TrackSwitchCommand> {

    @Override
    public TrackSwitchCommand deserialize(SerializedCommand command) {
        ComponentPin pin = command.component().getPin();
        Direction direction = getDirectionByName(command);

        return new TrackSwitchCommand(pin, direction);
    }

    private Direction getDirectionByName(SerializedCommand command) {
        try {
            return Direction.valueOf(command.action());
        } catch (IllegalArgumentException e) {
            throw new CommandDeserializationException("Failed to deserialize command " + command.toString());
        }
    }

}