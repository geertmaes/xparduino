package com.cegeka.xpdays.arduino.event.dispatch;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class EventDispatcherTest {

    private static final String INVALID_PAYLOAD = "invalid payload";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private EventDispatcher eventDispatcher;
    @Mock
    private SerializedEventFactory serializedEventFactory;

    @Test
    public void dispatch_ThrowsException_WhenDeserializationFails() throws Exception {
        when(serializedEventFactory.create(INVALID_PAYLOAD))
                .thenThrow(new EventDeserializationException());

        assertThatThrownBy(() -> eventDispatcher.dispatch(INVALID_PAYLOAD))
                .isInstanceOf(EventDispatchingException.class)
                .hasCauseInstanceOf(EventDeserializationException.class);
    }
}