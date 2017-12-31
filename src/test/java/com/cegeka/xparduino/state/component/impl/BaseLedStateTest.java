package com.cegeka.xparduino.state.component.impl;

import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.event.EventTestConstants.baseLedEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class BaseLedStateTest {

    private BaseLedState state = new BaseLedState(DIGITAL_0);

    @Test
    public void baseLedState_Default() throws Exception {
        assertThat(state.isEmitting()).isFalse();
    }

    @Test
    public void baseLedState_Emitting_True() throws Exception {
        state.on(baseLedEvent(true));

        assertThat(state.isEmitting()).isTrue();
    }

    @Test
    public void baseLedState_Emitting_False() throws Exception {
        state.on(baseLedEvent(false));

        assertThat(state.isEmitting()).isFalse();
    }

}
