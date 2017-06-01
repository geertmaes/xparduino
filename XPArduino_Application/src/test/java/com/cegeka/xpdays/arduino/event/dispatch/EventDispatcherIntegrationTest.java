package com.cegeka.xpdays.arduino.event.dispatch;

import com.cegeka.xpdays.arduino.component.Component;
import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.event.Event;
import com.cegeka.xpdays.arduino.event.EventCode;
import com.cegeka.xpdays.arduino.event.EventDeserializer;
import com.cegeka.xpdays.arduino.event.EventMapping;
import org.junit.Test;

import static com.cegeka.xpdays.arduino.event.EventCode.BASE_LED_EVENT;
import static com.cegeka.xpdays.arduino.event.EventCode.INFRA_LED_EVENT;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class EventDispatcherIntegrationTest {

    private static final String BODY = "test";
    private static final EventCode EVENT_CODE = EventCode.BASE_LED_EVENT;
    private static final EventCode ANOTHER_EVENT_CODE = EventCode.INFRA_LED_EVENT;
    private static final Component COMPONENT = new Component(0, ComponentType.BASE_LED);

    @Test
    public void dispatch() throws Exception {
        EventDispatcher eventDispatcher = new EventDispatcher();
        TriggerEventListener<TestEvent> listener = new TriggerEventListener<>();
        eventDispatcher.registerEventListener(listener);

        eventDispatcher.dispatch(format("<%d:%d,%d,%s>",
                COMPONENT.getType().getValue(),
                COMPONENT.getPin(),
                EVENT_CODE.getValue(),
                BODY));

        assertThat(listener.isTriggered()).isTrue();
    }

    @Test
    public void dispatch_WhenNoMapperSpecified_UseDefaultDeserializer() throws Exception {
        EventDispatcher eventDispatcher = new EventDispatcher();
        TriggerEventListener<TestEventWithoutMapper> listener = new TriggerEventListener<>();
        eventDispatcher.registerEventListener(listener);

        eventDispatcher.dispatch(format("<%d:%d,%d,%s>",
                COMPONENT.getType().getValue(),
                COMPONENT.getPin(),
                ANOTHER_EVENT_CODE.getValue(),
                BODY));

        assertThat(listener.isTriggered()).isTrue();
    }

    @Test
    public void dispatch_CallsParentAndChildClassesOfEvent() throws Exception {
        EventDispatcher eventDispatcher = new EventDispatcher();
        ParentAndChildEventListener listener = new ParentAndChildEventListener();
        eventDispatcher.registerEventListener(listener);

        eventDispatcher.dispatch(format("<%d:%d,%d,%s>",
                COMPONENT.getType().getValue(),
                COMPONENT.getPin(),
                EVENT_CODE.getValue(),
                BODY));

        assertThat(listener.getCounter()).isEqualTo(2);
    }

    @Test
    public void dispatch_WhenListenerThrowsException_DoesNothing() throws Exception {
        EventDispatcher eventDispatcher = new EventDispatcher();
        eventDispatcher.registerEventListener(new ThrowingEventListener());

        eventDispatcher.dispatch(format("<%d:%d,%d,%s>",
                COMPONENT.getType().getValue(),
                COMPONENT.getPin(),
                EVENT_CODE.getValue(),
                BODY));
    }

    @EventMapping(value = INFRA_LED_EVENT)
    public static class TestEventWithoutMapper extends Event {

        public TestEventWithoutMapper(int pin) {
            super(pin);
        }
    }

    @EventMapping(value = BASE_LED_EVENT, mapper = TestEventDeserializer.class)
    static class TestEvent extends Event {

        public TestEvent(int pin) {
            super(pin);
        }
    }

    @EventMapping(value = BASE_LED_EVENT, mapper = TestEventDeserializer.class)
    static class TestChildEvent extends TestEvent {

        public TestChildEvent(int pin) {
            super(pin);
        }
    }

    static class TestEventDeserializer implements EventDeserializer<TestEvent> {

        @Override
        public TestEvent deserialize(SerializedEvent event) {
            return new TestEvent(event.getComponent().getPin());
        }
    }

    private static class TriggerEventListener<T extends Event> implements EventListener {

        private boolean triggered = false;

        boolean isTriggered() {
            return triggered;
        }

        public void on(T event) {
            triggered = true;
        }
    }

    private static class ParentAndChildEventListener implements EventListener {

        private int counter = 0;

        public int getCounter() {
            return counter;
        }

        public void on(TestEvent event) {
            counter++;
        }

        public void on(TestChildEvent event) {
            counter++;
        }
    }

    private static class ThrowingEventListener implements EventListener {

        public void on(TestEvent event) {
            throw new RuntimeException();
        }
    }
}