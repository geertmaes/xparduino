package com.cegeka.xparduino.state.component.impl;

import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.event.EventTestConstants.rfidReaderEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class RfidReaderStateTest {

    private static final String TAG_ID = "01234567891234";

    private RfidReaderState state = new RfidReaderState(DIGITAL_0);

    @Test
    public void rfidReaderState_Default() throws Exception {
        assertThat(state.getTagId()).isNull();
    }

    @Test
    public void rfidReaderState_TagId() throws Exception {
        state.on(rfidReaderEvent(TAG_ID));

        assertThat(state.getTagId()).isEqualTo(TAG_ID);
    }

}
