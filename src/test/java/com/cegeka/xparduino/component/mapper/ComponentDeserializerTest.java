package com.cegeka.xparduino.component.mapper;

import com.cegeka.xparduino.component.Component;
import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.component.ComponentType.BASE_LED;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ComponentDeserializerTest {

    @Test
    public void deserialize() throws Exception {
        ComponentDeserializer deserializer = new ComponentDeserializer();

        assertThat(deserializer.deserialize(format("%d:%d", BASE_LED.getValue(), DIGITAL_0.value())))
                .isEqualTo(new Component(DIGITAL_0, BASE_LED));
    }

    @Test
    public void deserialize_ThrowsExceptionWhenFormatIsInvalid() throws Exception {
        ComponentDeserializer deserializer = new ComponentDeserializer();

        assertThatThrownBy(() -> deserializer.deserialize("invalid format"))
                .isInstanceOf(ComponentFormatException.class)
                .hasMessage("Component (invalid format) has invalid format");
    }

    @Test
    public void deserialize_ThrowsExceptionWhenPayloadIsNull() throws Exception {
        ComponentDeserializer deserializer = new ComponentDeserializer();

        assertThatThrownBy(() -> deserializer.deserialize(null))
                .isInstanceOf(NullPointerException.class);
    }
}