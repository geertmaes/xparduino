package com.cegeka.xparduino.event.serialized;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.event.EventCode;
import org.junit.Test;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SerializedEventFactoryTest {

    private static final String EVENT_BODY = "test";
    private static final EventCode EVENT_CODE = EventCode.BASE_LED_EVENT;
    private static final Component COMPONENT = new Component(0, ComponentType.BASE_LED);

    @Test
    public void create() throws Exception {
        SerializedEventFactory deserializer = new SerializedEventFactory();

        SerializedEvent actual = deserializer
                .create(format("<%d:%d,%s,%s>",
                        COMPONENT.getType().getValue(),
                        COMPONENT.getPin(),
                        EVENT_CODE.value(),
                        EVENT_BODY));

        assertThat(actual).isEqualTo(new SerializedEvent(EVENT_CODE, EVENT_BODY, COMPONENT));
    }

    @Test
    public void create_ThrowsException_WhenInvalidFormat() throws Exception {
        SerializedEventFactory deserializer = new SerializedEventFactory();

        assertThatThrownBy(() -> deserializer.create("invalid format"))
                .isInstanceOf(SerializedEventFormatException.class)
                .hasMessage("Event (invalid format) has invalid format");
    }

}