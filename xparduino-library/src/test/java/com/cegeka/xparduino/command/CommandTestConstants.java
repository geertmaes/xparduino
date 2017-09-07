package com.cegeka.xparduino.command;

import com.cegeka.xparduino.command.impl.baseled.BaseLedCommand;
import com.cegeka.xparduino.command.impl.trackswitch.TrackSwitchCommand;
import com.cegeka.xparduino.domain.Direction;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;

public class CommandTestConstants {

    public static BaseLedCommand baseLedCommand(boolean emitting) {
        return new BaseLedCommand(DIGITAL_0, emitting);
    }

    public static TrackSwitchCommand trackSwitchCommand(Direction direction) {
        return new TrackSwitchCommand(DIGITAL_0, direction);
    }

}
