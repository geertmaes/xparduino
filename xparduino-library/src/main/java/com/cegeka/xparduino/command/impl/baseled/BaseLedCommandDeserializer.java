package com.cegeka.xparduino.command.impl.baseled;

import com.cegeka.xparduino.command.CommandDataDeserializer;
import com.cegeka.xparduino.command.mapper.deserializer.CommandDeserializationException;
import com.cegeka.xparduino.command.serialized.SerializedCommand;
import com.cegeka.xparduino.component.ComponentPin;

public class BaseLedCommandDeserializer implements CommandDataDeserializer<BaseLedCommand> {

    private static final String ON_ACTION = "ON";
    private static final String OFF_ACTION = "OFF";

    @Override
    public BaseLedCommand deserialize(SerializedCommand command) {
        ComponentPin pin = command.component().getPin();
        boolean emitting = extractEmitting(command);
        return new BaseLedCommand(pin, emitting);
    }

    private boolean extractEmitting(SerializedCommand command) {
        String action = command.action();

        if (action.equals(ON_ACTION)) {
            return true;
        }

        if (action.equals(OFF_ACTION)) {
            return false;
        }

        throw new CommandDeserializationException("Failed to deserialize command " + command.toString());
    }

}