package com.cegeka.xparduino.command.behaviour;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.event.Event;

public interface CommandBehaviour<C extends Command, E extends Event> {

    E create(C command);

    boolean canHandle(Command command);
}
