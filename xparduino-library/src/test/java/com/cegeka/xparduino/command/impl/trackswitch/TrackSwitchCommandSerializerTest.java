package com.cegeka.xparduino.command.impl.trackswitch;

import com.cegeka.xparduino.command.serialized.SerializedCommand;
import com.cegeka.xparduino.domain.Direction;
import org.junit.Test;

import static com.cegeka.xparduino.command.CommandTestConstants.TrackSwitch.*;
import static com.cegeka.xparduino.domain.Direction.LEFT;
import static org.assertj.core.api.Assertions.assertThat;

public class TrackSwitchCommandSerializerTest {

    private final TrackSwitchCommandSerializer serializer = new TrackSwitchCommandSerializer();

    @Test
    public void serialize_Direction_Left() throws Exception {
        SerializedCommand command = serializer
                .serialize(trackSwitchCommand(LEFT));

        assertThat(command)
                .isEqualTo(serializedLeftTrackSwitchCommand());
    }

    @Test
    public void serialize_Direction_Right() throws Exception {
        SerializedCommand command = serializer
                .serialize(trackSwitchCommand(Direction.RIGHT));

        assertThat(command)
                .isEqualTo(serializedRightTrackSwitchCommand());
    }

}