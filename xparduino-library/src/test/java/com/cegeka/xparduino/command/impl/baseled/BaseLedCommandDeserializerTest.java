package com.cegeka.xparduino.command.impl.baseled;

import com.cegeka.xparduino.command.mapper.deserializer.CommandDeserializationException;
import com.cegeka.xparduino.command.serialized.SerializedCommand;
import org.junit.Test;

import static com.cegeka.xparduino.command.CommandCode.BASE_LED_COMMAND;
import static com.cegeka.xparduino.command.CommandTestConstants.baseLedCommand;
import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.component.ComponentTestConstants.baseLed;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BaseLedCommandDeserializerTest {

    private static final String ON_ACTION = "ON";
    private static final String OFF_ACTION = "OFF";
    private static final String INVALID_ACTION = "invalid";

    private final BaseLedCommandDeserializer deserializer = new BaseLedCommandDeserializer();

    @Test
    public void deserialize_Emitting_True() throws Exception {
        BaseLedCommand command = deserializer
                .deserialize(serializeBaseLedCommand(ON_ACTION));

        assertThat(command).isEqualTo(baseLedCommand(true));
    }

    @Test
    public void deserialize_Emitting_False() throws Exception {
        BaseLedCommand command = deserializer
                .deserialize(serializeBaseLedCommand(OFF_ACTION));

        assertThat(command).isEqualTo(baseLedCommand(false));
    }

    @Test
    public void deserialize_InvalidAction() throws Exception {
        assertThatThrownBy(() -> deserializer.deserialize(serializeBaseLedCommand(INVALID_ACTION)))
                .isInstanceOf(CommandDeserializationException.class);
    }

    private SerializedCommand serializeBaseLedCommand(String action) {
        return new SerializedCommand(BASE_LED_COMMAND, action, baseLed(DIGITAL_0));
    }

}