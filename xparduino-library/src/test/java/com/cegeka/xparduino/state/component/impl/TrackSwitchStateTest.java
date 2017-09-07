package com.cegeka.xparduino.state.component.impl;

import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.domain.Direction.LEFT;
import static com.cegeka.xparduino.domain.Direction.RIGHT;
import static com.cegeka.xparduino.event.EventTestConstants.trackSwitchEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class TrackSwitchStateTest {

    private TrackSwitchState state = new TrackSwitchState(DIGITAL_0);

    @Test
    public void trackSwitchState_Default() throws Exception {
        assertThat(state.getDirection()).isNull();
    }

    @Test
    public void trackSwitchState_Direction_Left() throws Exception {
        state.on(trackSwitchEvent(LEFT));

        assertThat(state.getDirection()).isEqualTo(LEFT);
    }

    @Test
    public void trackSwitchState_Direction_Right() throws Exception {
        state.on(trackSwitchEvent(RIGHT));

        assertThat(state.getDirection()).isEqualTo(RIGHT);
    }

}
