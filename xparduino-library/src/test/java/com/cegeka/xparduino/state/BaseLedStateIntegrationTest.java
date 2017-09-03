package com.cegeka.xparduino.state;

import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.ArduinoTestRule;
import com.cegeka.xparduino.state.component.impl.BaseLedState;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentTestConstants.PIN_1;
import static com.cegeka.xparduino.component.ComponentTestConstants.baseLed;
import static com.cegeka.xparduino.event.EventTestConstants.baseLedEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class BaseLedStateIntegrationTest {

    @Rule
    public ArduinoTestRule arduinoRule = new ArduinoTestRule(baseLed(PIN_1));

    private Arduino arduino;

    @Before
    public void setUp() throws Exception {
        arduino = arduinoRule.arduino();
    }

    @Test
    public void baseLedState_Default() throws Exception {
        BaseLedState state = arduino.getState(PIN_1, BaseLedState.class);

        assertThat(state.isEmitting()).isFalse();
    }

    @Test
    public void baseLedState_Emitting_True() throws Exception {
        BaseLedState state = arduino.getState(PIN_1, BaseLedState.class);
        state.on(baseLedEvent(false));

        arduino.baseLed(PIN_1)
                .withEmitting(true)
                .execute();

        assertThat(state.isEmitting()).isTrue();
    }

    @Test
    public void baseLedState_Emitting_False() throws Exception {
        BaseLedState state = arduino.getState(PIN_1, BaseLedState.class);
        state.on(baseLedEvent(true));

        arduino.baseLed(PIN_1)
                .withEmitting(false)
                .execute();

        assertThat(state.isEmitting()).isFalse();
    }

}
