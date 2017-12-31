package com.cegeka.xparduino.command.impl.trackswitch;

import com.cegeka.xparduino.command.mapper.deserializer.CommandDeserializationException;
import com.cegeka.xparduino.command.serialized.SerializedCommand;
import com.cegeka.xparduino.component.ComponentTestConstants;
import org.junit.Test;

import static com.cegeka.xparduino.command.CommandCode.BASE_LED_COMMAND;
import static com.cegeka.xparduino.command.CommandCode.TRACK_SWITCH_COMMAND;
import static com.cegeka.xparduino.command.CommandTestConstants.TrackSwitch.*;
import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.component.ComponentTestConstants.baseLed;
import static com.cegeka.xparduino.component.ComponentTestConstants.trackSwitch;
import static com.cegeka.xparduino.domain.Direction.LEFT;
import static com.cegeka.xparduino.domain.Direction.RIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TrackSwitchCommandDeserializerTest {

    private static final String INVALID_ACTION = "invalid";

    private final TrackSwitchCommandDeserializer deserializer = new TrackSwitchCommandDeserializer();

    @Test
    public void deserialize_Direction_Left() throws Exception {
        TrackSwitchCommand command = deserializer
                .deserialize(serializedLeftTrackSwitchCommand());

        assertThat(command)
                .isEqualTo(trackSwitchCommand(LEFT));
    }

    @Test
    public void deserialize_Direction_Right() throws Exception {
        TrackSwitchCommand command = deserializer
                .deserialize(serializedRightTrackSwitchCommand());

        assertThat(command)
                .isEqualTo(trackSwitchCommand(RIGHT));
    }

    @Test
    public void deserialize_InvalidAction() throws Exception {
        assertThatThrownBy(() -> deserializer.deserialize(serializeTrackSwitchCommand(INVALID_ACTION)))
                .isInstanceOf(CommandDeserializationException.class);
    }

    private SerializedCommand serializeTrackSwitchCommand(String action) {
        return new SerializedCommand(TRACK_SWITCH_COMMAND, action, trackSwitch(DIGITAL_0));
    }

}