package com.cegeka.xparduino.state.component.impl;

import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.ArduinoTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentTestConstants.PIN_1;
import static com.cegeka.xparduino.component.ComponentTestConstants.rfidReader;
import static com.cegeka.xparduino.event.EventTestConstants.rfidReaderEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class RfidReaderStateIntegrationTest {

    private static final String TAG_ID = "01234567891234";

    @Rule
    public ArduinoTestRule arduinoRule = new ArduinoTestRule(rfidReader(PIN_1));

    private Arduino arduino;

    @Before
    public void setUp() throws Exception {
        arduino = arduinoRule.arduino();
    }

    @Test
    public void rfidReaderState_Default() throws Exception {
        RfidReaderState state = arduino.getState(PIN_1, RfidReaderState.class);

        assertThat(state.getTagId()).isNullOrEmpty();
    }

    @Test
    public void rfidReaderState_TagId() throws Exception {
        RfidReaderState state = arduino.getState(PIN_1, RfidReaderState.class);

        arduinoRule.emitEvents(rfidReaderEvent(TAG_ID));

        assertThat(state.getTagId()).isEqualTo(TAG_ID);
    }

}
