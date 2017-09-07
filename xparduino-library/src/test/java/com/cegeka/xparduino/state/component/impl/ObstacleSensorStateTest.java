package com.cegeka.xparduino.state.component.impl;

import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.event.EventTestConstants.obstacleSensorEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class ObstacleSensorStateTest {

    private ObstacleSensorState state = new ObstacleSensorState(DIGITAL_0);

    @Test
    public void obstacleSensorState_Default() throws Exception {
        assertThat(state.isBlocked()).isFalse();
    }

    @Test
    public void obstacleSensorState_Blocked_True() throws Exception {
        state.on(obstacleSensorEvent(true));

        assertThat(state.isBlocked()).isTrue();
    }

    @Test
    public void obstacleSensorState_Blocked_False() throws Exception {
        state.on(obstacleSensorEvent(false));

        assertThat(state.isBlocked()).isFalse();
    }

}
