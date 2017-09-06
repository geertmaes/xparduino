package com.cegeka.xparduino.component.mapper;

import com.cegeka.xparduino.component.Component;
import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.component.ComponentType.BASE_LED;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ComponentSerializerTest {

    @Test
    public void serialize() throws Exception {
        ComponentSerializer serializer = new ComponentSerializer();

        String serialized = serializer.serialize(new Component(DIGITAL_0, BASE_LED));

        assertThat(serialized)
                .isEqualTo(format("%d:%d", BASE_LED.getValue(), DIGITAL_0.value()));
    }

    @Test
    public void serialize_ThrowsExceptionWhenComponentIsNull() throws Exception {
        ComponentSerializer serializer = new ComponentSerializer();

        assertThatThrownBy(() -> serializer.serialize(null))
                .isInstanceOf(NullPointerException.class);
    }

}