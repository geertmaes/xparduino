package com.cegeka.xparduino.state.component.impl;

import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.ArduinoTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.component.ComponentTestConstants.obstacleSensor;
import static com.cegeka.xparduino.event.EventTestConstants.obstacleSensorEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class ObstacleSensorStateIntegrationTest {

    @Rule
    public ArduinoTestRule arduinoRule = new ArduinoTestRule(obstacleSensor(DIGITAL_0));

    private Arduino arduino;

    @Before
    public void setUp() throws Exception {
        arduino = arduinoRule.arduino();
    }

    @Test
    public void obstacleSensorState_Default() throws Exception {
        ObstacleSensorState state = arduino.getState(DIGITAL_0, ObstacleSensorState.class);

        assertThat(state.isBlocked()).isFalse();
    }

    @Test
    public void obstacleSensorState_Blocked_True() throws Exception {
        ObstacleSensorState state = arduino.getState(DIGITAL_0, ObstacleSensorState.class);
        state.on(obstacleSensorEvent(false));

        arduinoRule.emitEvents(obstacleSensorEvent(true));

        assertThat(state.isBlocked()).isTrue();
    }

    @Test
    public void obstacleSensorState_Blocked_False() throws Exception {
        ObstacleSensorState state = arduino.getState(DIGITAL_0, ObstacleSensorState.class);
        state.on(obstacleSensorEvent(true));

        arduinoRule.emitEvents(obstacleSensorEvent(false));

        assertThat(state.isBlocked()).isFalse();
    }

}
