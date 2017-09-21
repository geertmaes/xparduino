package com.cegeka.xparduino.state.component.impl;

import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.ArduinoTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.component.ComponentTestConstants.photoSensor;
import static com.cegeka.xparduino.event.EventTestConstants.photoSensorEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class PhotoSensorStateIntegrationTest {

    private static final int SIGNAL_STRENGTH = 10;

    @Rule
    public ArduinoTestRule arduinoRule = new ArduinoTestRule(photoSensor(DIGITAL_0));

    private Arduino arduino;

    @Before
    public void setUp() throws Exception {
        arduino = arduinoRule.arduino();
    }

    @Test
    public void photoSensorState_Default() throws Exception {
        PhotoSensorState state = arduino.getState(DIGITAL_0, PhotoSensorState.class);

        assertThat(state.getSignal()).isEqualTo(0);
    }

    @Test
    public void photoSensorState_Signal() throws Exception {
        PhotoSensorState state = arduino.getState(DIGITAL_0, PhotoSensorState.class);

        arduinoRule.emitEvents(photoSensorEvent(SIGNAL_STRENGTH));

        assertThat(state.getSignal()).isEqualTo(SIGNAL_STRENGTH);
    }

}
