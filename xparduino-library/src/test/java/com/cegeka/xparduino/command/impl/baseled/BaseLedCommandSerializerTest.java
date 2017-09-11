package com.cegeka.xparduino.command.impl.baseled;

import com.cegeka.xparduino.command.serialized.SerializedCommand;
import org.junit.Test;

import static com.cegeka.xparduino.command.CommandCode.BASE_LED_COMMAND;
import static com.cegeka.xparduino.command.CommandTestConstants.BaseLed.baseLedCommand;
import static com.cegeka.xparduino.command.CommandTestConstants.BaseLed.serializedEmittingBaseLedCommand;
import static com.cegeka.xparduino.command.CommandTestConstants.BaseLed.serializedNotEmittingBaseLedCommand;
import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.component.ComponentTestConstants.baseLed;
import static org.assertj.core.api.Assertions.assertThat;

public class BaseLedCommandSerializerTest {

    private final BaseLedCommandSerializer serializer = new BaseLedCommandSerializer();

    @Test
    public void serialize_Emitting_True() throws Exception {
        SerializedCommand command = serializer
                .serialize(baseLedCommand(true));

        assertThat(command)
                .isEqualTo(serializedEmittingBaseLedCommand());
    }

    @Test
    public void serialize_Emitting_False() throws Exception {
        SerializedCommand command = serializer
                .serialize(baseLedCommand(false));

        assertThat(command)
                .isEqualTo(serializedNotEmittingBaseLedCommand());
    }

}