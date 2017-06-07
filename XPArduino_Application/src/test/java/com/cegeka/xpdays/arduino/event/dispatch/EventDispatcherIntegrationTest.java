package com.cegeka.xpdays.arduino.event.dispatch;

import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventCode;
import com.cegeka.xpdays.arduino.event.EventListener;
import com.cegeka.xpdays.arduino.event.EventMapping;
import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializer;
import com.cegeka.xpdays.arduino.event.deserialiser.EventDeserializerMapping;
import com.cegeka.xpdays.arduino.component.Component;
import com.cegeka.xpdays.arduino.component.ComponentType;
import org.junit.Before;
import org.junit.Test;

import static com.cegeka.xpdays.arduino.event.EventCode.BASE_LED_EVENT;
import static com.cegeka.xpdays.arduino.event.EventCode.INFRARED_EMITTER_EVENT;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class EventDispatcherIntegrationTest {

    private static final String SCAN_PACKAGE = EventDispatcherIntegrationTest.class.getName();

    private static final String BODY = "test";
    private static final EventCode EVENT_CODE = EventCode.BASE_LED_EVENT;
    private static final EventCode ANOTHER_EVENT_CODE = EventCode.INFRARED_EMITTER_EVENT;
    private static final Component COMPONENT = new Component(0, ComponentType.BASE_LED);

    private EventDispatcher eventDispatcher;

    @Before
    public void before() {
        eventDispatcher = EventDispatcher.fromPackage(SCAN_PACKAGE);
    }

    @Test
    public void dispatch() throws Exception {
        EventListenerStub<TestEvent> listener = new EventListenerStub<>();
        eventDispatcher.registerListener(listener);

        eventDispatcher.dispatch(eventAsString());

        assertThat(listener.isTriggered()).isTrue();
    }

    @Test
    public void dispatch_WhenNoMapperSpecified_UseDefaultDeserializer() throws Exception {
        EventListenerStub<TestEventWithoutMapper> listener = new EventListenerStub<>();
        eventDispatcher.registerListener(listener);

        eventDispatcher.dispatch(eventAsString(ANOTHER_EVENT_CODE));

        assertThat(listener.isTriggered()).isTrue();
    }

    @Test
    public void dispatch_CallsParentAndChildClassesOfEvent() throws Exception {
        InheritanceEventListener listener = new InheritanceEventListener();
        eventDispatcher.registerListener(listener);

        eventDispatcher.dispatch(eventAsString());

        assertThat(listener.getCounter()).isEqualTo(2);
    }

    @Test
    public void dispatch_WhenListenerThrowsException_DoesNothing() throws Exception {
        eventDispatcher.registerListener(new ThrowingEventListener());

        assertThatCode(() -> eventDispatcher.dispatch(eventAsString()))
                .doesNotThrowAnyException();
    }

    private String eventAsString() {
        return eventAsString(EVENT_CODE);
    }

    private String eventAsString(EventCode eventCode) {
        return format("<%d:%d,%d,%s>",
                COMPONENT.getType().getValue(),
                COMPONENT.getPin(),
                eventCode.getValue(),
                BODY);
    }

    @EventMapping(BASE_LED_EVENT)
    static class TestEvent implements Event {

        private int pin;

        public TestEvent(int pin) {
            this.pin = pin;
        }

        @Override
        public int getPin() {
            return pin;
        }
    }

    @EventMapping(INFRARED_EMITTER_EVENT)
    public static class TestEventWithoutMapper implements Event {

        private int pin;

        public TestEventWithoutMapper(int pin) {
            this.pin = pin;
        }

        @Override
        public int getPin() {
            return pin;
        }
    }

    @EventDeserializerMapping(TestEvent.class)
    static class TestEventDeserializer implements EventDeserializer<TestEvent> {

        @Override
        public TestEvent deserialize(SerializedEvent event) {
            return new TestEvent(event.component().getPin());
        }
    }

    private static class EventListenerStub<T extends Event> implements EventListener {

        private boolean triggered = false;

        boolean isTriggered() {
            return triggered;
        }

        public void on(T event) {
            triggered = true;
        }
    }

    private static class InheritanceEventListener implements EventListener {

        private int counter = 0;

        public int getCounter() {
            return counter;
        }

        public void on(Event event) {
            counter++;
        }

        public void on(TestEvent event) {
            counter++;
        }
    }

    private static class ThrowingEventListener implements EventListener {

        public void on(TestEvent event) {
            throw new RuntimeException();
        }
    }
}