package com.cegeka.xparduino.event.dispatch;

import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.EventCode;
import com.cegeka.xparduino.event.serialize.SerializedEvent;
import com.cegeka.xparduino.event.deserialize.EventDeserializer;
import com.cegeka.xparduino.event.EventMapping;
import com.cegeka.xparduino.event.deserialize.EventDeserializerMapping;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class EventDispatcherStateTest {

    private static final String SCAN_PACKAGE = EventDispatcherStateTest.class.getName();

    @Test
    public void getEventClassByEventCode() throws Exception {
        EventDispatcherState state =
                EventDispatcherState.scanPackage(SCAN_PACKAGE);

        Optional<Class<? extends Event>> event = state.getEventClassByEventCode(EventCode.BASE_LED_EVENT);

        assertThat(event)
                .contains(TestEvent.class);
    }

    @Test
    public void getEventDeserializerByEventClass() throws Exception {
        EventDispatcherState state =
                EventDispatcherState.scanPackage(SCAN_PACKAGE);

        EventDeserializer serializer = state.getEventDeserializerByEventClass(TestEvent.class);

        assertThat(serializer)
                .isInstanceOf(TestEventDeserializer.class);
    }

    @EventMapping(EventCode.BASE_LED_EVENT)
    public static class TestEvent implements Event {

        @Override
        public int getPin() {
            return 0;
        }
    }

    @EventDeserializerMapping(TestEvent.class)
    public static class TestEventDeserializer implements EventDeserializer<TestEvent> {

        @Override
        public TestEvent deserialize(SerializedEvent event) {
            return null;
        }
    }
}