package com.cegeka.xparduino.command.serialization;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.component.Component;
import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentType.BASE_LED;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CommandSerializerTest {

    @Test
    public void serialize() throws Exception {
        CommandSerializer serializer = new CommandSerializer();
        BaseLedTestCommand command = new BaseLedTestCommand();

        String serialized = serializer.serialize(command);

        assertThat(serialized)
                .isEqualTo(format("<%s:%s,%s>",
                        command.getComponent().getType().getValue(),
                        command.getComponent().getPin(),
                        command.getAction()));
    }

    @Test
    public void serialize_ThrowsExceptionWhenComponentIsNull() throws Exception {
        CommandSerializer serializer = new CommandSerializer();

        assertThatThrownBy(() -> serializer.serialize(null))
                .isInstanceOf(NullPointerException.class);
    }

    private static class BaseLedTestCommand implements Command {

        @Override
        public String getAction() {
            return "TEST";
        }

        @Override
        public Component getComponent() {
            return new Component(5, BASE_LED);
        }

    }
}