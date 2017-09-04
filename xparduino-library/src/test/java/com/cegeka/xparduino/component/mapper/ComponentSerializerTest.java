package com.cegeka.xparduino.component.mapper;

import com.cegeka.xparduino.component.Component;
import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentType.BASE_LED;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ComponentSerializerTest {

    @Test
    public void serialize() throws Exception {
        ComponentSerializer serializer = new ComponentSerializer();

        String serialized = serializer.serialize(new Component(5, BASE_LED));

        assertThat(serialized)
                .isEqualTo(format("%d:%d", BASE_LED.getValue(), 5));
    }

    @Test
    public void serialize_ThrowsExceptionWhenComponentIsNull() throws Exception {
        ComponentSerializer serializer = new ComponentSerializer();

        assertThatThrownBy(() -> serializer.serialize(null))
                .isInstanceOf(NullPointerException.class);
    }

}