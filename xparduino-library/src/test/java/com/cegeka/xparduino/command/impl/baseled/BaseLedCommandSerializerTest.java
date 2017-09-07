package com.cegeka.xparduino.command.impl.baseled;

import com.cegeka.xparduino.command.serialized.SerializedCommand;
import org.junit.Test;

import static com.cegeka.xparduino.command.CommandCode.BASE_LED_COMMAND;
import static com.cegeka.xparduino.command.CommandTestConstants.baseLedCommand;
import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.component.ComponentTestConstants.baseLed;
import static org.assertj.core.api.Assertions.assertThat;

public class BaseLedCommandSerializerTest {

    private static final String ON_ACTION = "ON";
    private static final String OFF_ACTION = "OFF";

    private final BaseLedCommandSerializer serializer = new BaseLedCommandSerializer();

    @Test
    public void serialize_Emitting_True() throws Exception {
        SerializedCommand command = serializer.serialize(baseLedCommand(true));

        assertThat(command).isEqualTo(new SerializedCommand(BASE_LED_COMMAND, ON_ACTION, baseLed(DIGITAL_0)));
    }

    @Test
    public void serialize_Emitting_False() throws Exception {
        SerializedCommand command = serializer.serialize(baseLedCommand(false));

        assertThat(command).isEqualTo(new SerializedCommand(BASE_LED_COMMAND, OFF_ACTION, baseLed(DIGITAL_0)));
    }

}