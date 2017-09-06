package com.cegeka.xparduino.state;

import com.cegeka.xparduino.state.component.impl.BaseLedState;
import com.cegeka.xparduino.state.component.impl.InfraredState;
import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_1;
import static com.cegeka.xparduino.component.ComponentTestConstants.*;
import static com.cegeka.xparduino.component.ComponentType.BASE_LED;
import static com.cegeka.xparduino.component.ComponentType.INFRARED_EMITTER;
import static com.cegeka.xparduino.state.ArduinoState.COMPONENT_PIN_MISMATCH;
import static com.cegeka.xparduino.state.ArduinoState.NO_COMPONENT_CONFIGURED;
import static com.cegeka.xparduino.state.ArduinoState.STATE_PIN_MISMATCH;
import static com.google.common.collect.Sets.newHashSet;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.*;

public class ArduinoStateTest {

    @Test
    public void getComponentStates() throws Exception {
        ArduinoState state = new ArduinoState(newHashSet(baseLed(DIGITAL_0)));

        assertThat(state.getComponentStates()).hasSize(1);
    }

    @Test
    public void getComponentStates_ByType() throws Exception {
        ArduinoState state = new ArduinoState(newHashSet(baseLed(DIGITAL_0)));

        assertThat(state.getComponentStates(BASE_LED)).hasSize(1);
        assertThat(state.getComponentStates(INFRARED_EMITTER)).hasSize(0);
    }

    @Test
    public void getState_whenValid() throws Exception {
        ArduinoState state = new ArduinoState(newHashSet(baseLed(DIGITAL_0)));

        assertThat(state.getState(DIGITAL_0, BaseLedState.class)).isNotNull();
    }

    @Test
    public void getState_whenInValidPin() throws Exception {
        ArduinoState state = new ArduinoState(newHashSet(baseLed(DIGITAL_0)));

        assertThatThrownBy(() -> state.getState(DIGITAL_1, BaseLedState.class))
                .isInstanceOf(ArduinoStateException.class)
                .hasMessage(format(NO_COMPONENT_CONFIGURED, DIGITAL_1));
    }

    @Test
    public void getState_whenInValidStateClass() throws Exception {
        ArduinoState state = new ArduinoState(newHashSet(baseLed(DIGITAL_0)));

        assertThatThrownBy(() -> state.getState(DIGITAL_0, InfraredState.class))
                .isInstanceOf(ArduinoStateException.class)
                .hasMessage(format(STATE_PIN_MISMATCH, BaseLedState.class, DIGITAL_0, InfraredState.class));
    }

    @Test
    public void validatePin_whenValid() throws Exception {
        ArduinoState state = new ArduinoState(newHashSet(baseLed(DIGITAL_0)));

        assertThatCode(() -> state.validatePin(DIGITAL_0))
                .doesNotThrowAnyException();
    }

    @Test
    public void validatePin_whenInValid() throws Exception {
        ArduinoState state = new ArduinoState(newHashSet(baseLed(DIGITAL_0)));

        assertThatThrownBy(() -> state.validatePin(DIGITAL_1))
                .isInstanceOf(ArduinoStateException.class)
                .hasMessage(format(NO_COMPONENT_CONFIGURED, DIGITAL_1));
    }

    @Test
    public void validateComponentOnPin_whenValid() throws Exception {
        ArduinoState state = new ArduinoState(newHashSet(baseLed(DIGITAL_0)));

        assertThatCode(() -> state.validateComponentOnPin(DIGITAL_0, BASE_LED))
                .doesNotThrowAnyException();
    }

    @Test
    public void validateComponentOnPin_whenInValidPin() throws Exception {
        ArduinoState state = new ArduinoState(newHashSet(baseLed(DIGITAL_0)));

        assertThatThrownBy(() -> state.validateComponentOnPin(DIGITAL_1, BASE_LED))
                .isInstanceOf(ArduinoStateException.class)
                .hasMessage(format(NO_COMPONENT_CONFIGURED, DIGITAL_1));
    }

    @Test
    public void validateComponentOnPin_whenInValidType() throws Exception {
        ArduinoState state = new ArduinoState(newHashSet(baseLed(DIGITAL_0)));

        assertThatThrownBy(() -> state.validateComponentOnPin(DIGITAL_0, INFRARED_EMITTER))
                .isInstanceOf(ArduinoStateException.class)
                .hasMessage(format(COMPONENT_PIN_MISMATCH, BASE_LED, DIGITAL_0, INFRARED_EMITTER));
    }

}