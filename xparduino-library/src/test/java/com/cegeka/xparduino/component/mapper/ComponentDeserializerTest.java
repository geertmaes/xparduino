package com.cegeka.xparduino.component.mapper;

import com.cegeka.xparduino.component.Component;
import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentType.BASE_LED;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ComponentDeserializerTest {

    @Test
    public void deserialize() throws Exception {
        ComponentDeserializer deserializer = new ComponentDeserializer();

        assertThat(deserializer.deserialize(format("%d:%d", BASE_LED.getValue(), 5)))
                .isEqualTo(new Component(5, BASE_LED));
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