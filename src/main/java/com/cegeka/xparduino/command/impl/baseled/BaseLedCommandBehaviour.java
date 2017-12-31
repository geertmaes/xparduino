package com.cegeka.xparduino.command.impl.baseled;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.behaviour.CommandBehaviour;
import com.cegeka.xparduino.event.impl.baseled.BaseLedEvent;

public class BaseLedCommandBehaviour implements CommandBehaviour<BaseLedCommand, BaseLedEvent> {

    @Override
    public BaseLedEvent createEvent(BaseLedCommand command) {
        return new BaseLedEvent(command.pin(), command.isEmitting());
    }

    @Override
    public boolean canHandle(Command command) {
        return BaseLedCommand.class.isInstance(command);
    }

}
