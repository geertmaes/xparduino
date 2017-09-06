package com.cegeka.xparduino.state.component.impl;

import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.ArduinoTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.component.ComponentTestConstants.baseLed;
import static com.cegeka.xparduino.event.EventTestConstants.baseLedEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class BaseLedStateIntegrationTest {

    @Rule
    public ArduinoTestRule arduinoRule = new ArduinoTestRule(baseLed(DIGITAL_0));

    private Arduino arduino;

    @Before
    public void setUp() throws Exception {
        arduino = arduinoRule.arduino();
    }

    @Test
    public void baseLedState_Default() throws Exception {
        BaseLedState state = arduino.getState(DIGITAL_0, BaseLedState.class);

        assertThat(state.isEmitting()).isFalse();
    }

    @Test
    public void baseLedState_Emitting_True() throws Exception {
        BaseLedState state = arduino.getState(DIGITAL_0, BaseLedState.class);
        state.on(baseLedEvent(false));

        arduinoRule.emitEvents(baseLedEvent(true));

        assertThat(state.isEmitting()).isTrue();
    }

    @Test
    public void baseLedState_Emitting_False() throws Exception {
        BaseLedState state = arduino.getState(DIGITAL_0, BaseLedState.class);
        state.on(baseLedEvent(true));

        arduinoRule.emitEvents(baseLedEvent(false));

        assertThat(state.isEmitting()).isFalse();
    }

}
