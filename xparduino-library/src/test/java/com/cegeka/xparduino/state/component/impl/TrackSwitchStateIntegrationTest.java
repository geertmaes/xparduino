package com.cegeka.xparduino.state.component.impl;

import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.ArduinoTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentTestConstants.PIN_1;
import static com.cegeka.xparduino.component.ComponentTestConstants.trackSwitch;
import static com.cegeka.xparduino.domain.Direction.LEFT;
import static com.cegeka.xparduino.domain.Direction.RIGHT;
import static com.cegeka.xparduino.event.EventTestConstants.trackSwitchEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class TrackSwitchStateIntegrationTest {

    @Rule
    public ArduinoTestRule arduinoRule = new ArduinoTestRule(trackSwitch(PIN_1));

    private Arduino arduino;

    @Before
    public void setUp() throws Exception {
        arduino = arduinoRule.arduino();
    }

    @Test
    public void trackSwitchState_Default() throws Exception {
        TrackSwitchState state = arduino.getState(PIN_1, TrackSwitchState.class);

        assertThat(state.getDirection()).isEqualTo(LEFT);
    }

    @Test
    public void trackSwitchState_Direction_Left() throws Exception {
        TrackSwitchState state = arduino.getState(PIN_1, TrackSwitchState.class);
        state.on(trackSwitchEvent(RIGHT));

        arduinoRule.emitEvents(trackSwitchEvent(LEFT));

        assertThat(state.getDirection()).isEqualTo(LEFT);
    }

    @Test
    public void trackSwitchState_Direction_Right() throws Exception {
        TrackSwitchState state = arduino.getState(PIN_1, TrackSwitchState.class);
        state.on(trackSwitchEvent(LEFT));

        arduinoRule.emitEvents(trackSwitchEvent(RIGHT));

        assertThat(state.getDirection()).isEqualTo(RIGHT);
    }

}
