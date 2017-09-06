package com.cegeka.xparduino.command.serialized;

import org.junit.Test;

import static com.cegeka.xparduino.command.CommandCode.BASE_LED_COMMAND;
import static com.cegeka.xparduino.component.ComponentTestConstants.PIN_1;
import static com.cegeka.xparduino.component.ComponentTestConstants.baseLed;
import static com.cegeka.xparduino.component.ComponentType.BASE_LED;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SerializedCommandFactoryTest {

    private static final String ACTION = "on";

    @Test
    public void create() throws Exception {
        SerializedCommandFactory factory = new SerializedCommandFactory();

        SerializedCommand actual = factory.create(format("<%d:%d,%d,%s>",
                BASE_LED.getValue(), PIN_1, BASE_LED_COMMAND.value(), ACTION));

        assertThat(actual).isEqualTo(new SerializedCommand(BASE_LED_COMMAND, ACTION, baseLed(PIN_1)));
    }

    @Test
    public void create_ThrowsException_WhenInvalidFormat() throws Exception {
        SerializedCommandFactory factory = new SerializedCommandFactory();

        assertThatThrownBy(() -> factory.create("invalid format"))
                .isInstanceOf(SerializedCommandFormatException.class)
                .hasMessage("Command (invalid format) has invalid format");
    }

}