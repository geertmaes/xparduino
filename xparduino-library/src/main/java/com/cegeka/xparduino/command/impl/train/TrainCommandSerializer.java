package com.cegeka.xparduino.command.impl.train;

import com.cegeka.xparduino.command.DefaultCommandSerializer;

import static com.cegeka.xparduino.command.CommandCode.TRAIN_COMMAND;

//TODO: write test
public class TrainCommandSerializer extends DefaultCommandSerializer<TrainCommand> {

    public TrainCommandSerializer() {
        super(TRAIN_COMMAND);
    }
}