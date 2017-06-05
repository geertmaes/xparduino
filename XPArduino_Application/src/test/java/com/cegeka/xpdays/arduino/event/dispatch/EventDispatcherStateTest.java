package com.cegeka.xpdays.arduino.event.dispatch;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventMapping;
import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializer;
import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializerMapping;
import org.junit.Test;

import static com.cegeka.xpdays.arduino.event.EventCode.BASE_LED_EVENT;
import static org.assertj.core.api.Assertions.assertThat;

public class EventDispatcherStateTest {

    private static final String SCAN_PACKAGE = EventDispatcherStateTest.class.getName();

    @Test
    public void getEvent() throws Exception {
        EventDispatcherState state =
                EventDispatcherState.fromPackage(SCAN_PACKAGE);

        Class<? extends Event> event = state.getEvent(BASE_LED_EVENT);

        assertThat(event)
                .isEqualTo(TestEvent.class);
    }

    @Test
    public void getEventSerializer() throws Exception {
        EventDispatcherState state =
                EventDispatcherState.fromPackage(SCAN_PACKAGE);

        EventDeserializer serializer = state.getEventSerializer(TestEvent.class);

        assertThat(serializer)
                .isInstanceOf(TestEventSerializer.class);
    }

    @EventMapping(BASE_LED_EVENT)
    public static class TestEvent implements Event {

        @Override
        public int getPin() {
            return 0;
        }
    }

    @EventDeserializerMapping(TestEvent.class)
    public static class TestEventSerializer implements EventDeserializer<TestEvent> {

        @Override
        public TestEvent deserialize(SerializedEvent event) {
            return null;
        }
    }
}