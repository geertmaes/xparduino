package com.cegeka.xparduino.state.component.impl;

import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.event.EventTestConstants.photoSensorEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class PhotoSensorStateTest {

    private static final int SIGNAL_STRENGTH = 10;

    private PhotoSensorState state = new PhotoSensorState(DIGITAL_0);

    @Test
    public void photoSensorState_Default() throws Exception {
        assertThat(state.getSignal()).isEqualTo(0);
    }

    @Test
    public void photoSensorState_Signal() throws Exception {
        state.on(photoSensorEvent(SIGNAL_STRENGTH));

        assertThat(state.getSignal()).isEqualTo(SIGNAL_STRENGTH);
    }

}
