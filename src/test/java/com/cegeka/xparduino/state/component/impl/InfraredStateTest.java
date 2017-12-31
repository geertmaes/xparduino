package com.cegeka.xparduino.state.component.impl;

import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.event.EventTestConstants.infraredEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class InfraredStateTest {

    private InfraredState state = new InfraredState(DIGITAL_0);

    @Test
    public void infraredState_Default() throws Exception {
        assertThat(state.isEmitting()).isFalse();
    }

    @Test
    public void infraredState_Emitting_True() throws Exception {
        state.on(infraredEvent(true));

        assertThat(state.isEmitting()).isTrue();
    }

    @Test
    public void infraredState_Emitting_False() throws Exception {
        state.on(infraredEvent(false));

        assertThat(state.isEmitting()).isFalse();
    }

}
