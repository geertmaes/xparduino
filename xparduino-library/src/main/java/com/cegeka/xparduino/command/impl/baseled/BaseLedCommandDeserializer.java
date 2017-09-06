package com.cegeka.xparduino.command.impl.baseled;

import com.cegeka.xparduino.command.CommandDataDeserializer;
import com.cegeka.xparduino.command.serialized.SerializedCommand;

public class BaseLedCommandDeserializer implements CommandDataDeserializer<BaseLedCommand> {

    @Override
    public BaseLedCommand deserialize(SerializedCommand command) {
        int pin = command.component().getPin();
        boolean emitting = Boolean.parseBoolean(command.action());
        return new BaseLedCommand(pin, emitting);
    }
}