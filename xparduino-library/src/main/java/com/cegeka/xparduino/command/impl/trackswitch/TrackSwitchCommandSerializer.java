package com.cegeka.xparduino.command.impl.trackswitch;

import com.cegeka.xparduino.command.DefaultCommandSerializer;

import static com.cegeka.xparduino.command.CommandCode.TRACK_SWITCH_COMMAND;

public class TrackSwitchCommandSerializer extends DefaultCommandSerializer {

    public TrackSwitchCommandSerializer() {
        super(TRACK_SWITCH_COMMAND);
    }
}