package com.cegeka.xparduino.command.impl.train;

import com.cegeka.xparduino.command.CommandDataDeserializer;
import com.cegeka.xparduino.command.serialized.SerializedCommand;
import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.domain.Color;

public class TrainCommandDeserializer implements CommandDataDeserializer<TrainCommand> {

    private static final String ACTION_PART_SEPARATOR = ":";

    @Override
    public TrainCommand deserialize(SerializedCommand command) {
        ComponentPin pin = command.component().getPin();
        String[] actionParts = command.action().split(ACTION_PART_SEPARATOR);

        Color color = Color.valueOf(actionParts[0]);
        int channel = Integer.parseInt(actionParts[1]);
        int speed = Integer.parseInt(actionParts[2]);

        return new TrainCommand(pin, speed, channel, color);
    }
}