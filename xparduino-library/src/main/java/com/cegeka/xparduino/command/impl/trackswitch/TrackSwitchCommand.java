package com.cegeka.xparduino.command.impl.trackswitch;


import com.cegeka.xparduino.command.AbstractCommand;
import com.cegeka.xparduino.command.CommandMapping;
import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.domain.Direction;

import static com.cegeka.xparduino.command.CommandCode.TRACK_SWITCH_COMMAND;

@CommandMapping(
        code = TRACK_SWITCH_COMMAND,
        serializer = TrackSwitchCommandSerializer.class,
        deserializer = TrackSwitchCommandDeserializer.class)
public class TrackSwitchCommand extends AbstractCommand {

    private final Direction direction;

    public TrackSwitchCommand(ComponentPin pin, Direction direction) {
        super(pin);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String getAction() {
        return direction.name();
    }

    @Override
    protected ComponentType getComponentType() {
        return ComponentType.TRACK_SWITCH;
    }

}
