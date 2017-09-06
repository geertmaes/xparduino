package com.cegeka.xparduino.command.behaviour;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.impl.baseled.BaseLedCommand;
import com.cegeka.xparduino.event.impl.baseled.BaseLedEvent;

public class BaseLedCommandBehaviour implements CommandBehaviour<BaseLedCommand, BaseLedEvent> {

    @Override
    public BaseLedEvent create(BaseLedCommand command) {
        return new BaseLedEvent(command.pin(), command.isEmitting());
    }

    @Override
    public boolean canHandle(Command command) {
        return BaseLedCommand.class.isInstance(command);
    }

}
