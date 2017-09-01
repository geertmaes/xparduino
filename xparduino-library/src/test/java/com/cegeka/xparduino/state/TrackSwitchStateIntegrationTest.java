package com.cegeka.xparduino.state;

import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.ArduinoTestRule;
import org.junit.Before;
import org.junit.Rule;

import static com.cegeka.xparduino.component.ComponentTestConstants.PIN_1;
import static com.cegeka.xparduino.component.ComponentTestConstants.trackSwitch;

public class TrackSwitchStateIntegrationTest {

    @Rule
    public ArduinoTestRule arduinoRule = new ArduinoTestRule(
            trackSwitch(PIN_1)
    );

    private Arduino arduino;

    @Before
    public void setUp() throws Exception {
        arduino = arduinoRule.arduino();
    }

//    @Test
//    public void trackSwitchState_Default() throws Exception {
//        TrackSwitchState state = arduino.getState(PIN_1, TrackSwitchState.class);
//
//        assertThat(state.getDirection()).isEqualTo(Direction.LEFT);
//    }
//
//    @Test
//    public void trackSwitchState_Direction_Left() throws Exception {
//
//    }
//
//    @Test
//    public void trackSwitchState_Direction_Right() throws Exception {
//
//    }

}
