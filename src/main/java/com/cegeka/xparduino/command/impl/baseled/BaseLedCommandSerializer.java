package com.cegeka.xparduino.command.impl.baseled;

import com.cegeka.xparduino.command.DefaultCommandSerializer;

import static com.cegeka.xparduino.command.CommandCode.BASE_LED_COMMAND;

public class BaseLedCommandSerializer extends DefaultCommandSerializer<BaseLedCommand> {

    public BaseLedCommandSerializer() {
        super(BASE_LED_COMMAND);
    }

}