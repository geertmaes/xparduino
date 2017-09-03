package com.cegeka.xparduino.state.component.impl;

import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.ArduinoTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentTestConstants.PIN_1;
import static com.cegeka.xparduino.component.ComponentTestConstants.infrared;
import static com.cegeka.xparduino.event.EventTestConstants.infraredEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class InfraredStateIntegrationTest {

    @Rule
    public ArduinoTestRule arduinoRule = new ArduinoTestRule(infrared(PIN_1));

    private Arduino arduino;

    @Before
    public void setUp() throws Exception {
        arduino = arduinoRule.arduino();
    }

    @Test
    public void infraredState_Default() throws Exception {
        InfraredState state = arduino.getState(PIN_1, InfraredState.class);

        assertThat(state.isEmitting()).isFalse();
    }

    @Test
    public void infraredState_Emitting_True() throws Exception {
        InfraredState state = arduino.getState(PIN_1, InfraredState.class);
        state.on(infraredEvent(false));

        arduinoRule.emitEvents(infraredEvent(true));

        assertThat(state.isEmitting()).isTrue();
    }

    @Test
    public void infraredState_Emitting_False() throws Exception {
        InfraredState state = arduino.getState(PIN_1, InfraredState.class);
        state.on(infraredEvent(true));

        arduinoRule.emitEvents(infraredEvent(false));

        assertThat(state.isEmitting()).isFalse();
    }

}
