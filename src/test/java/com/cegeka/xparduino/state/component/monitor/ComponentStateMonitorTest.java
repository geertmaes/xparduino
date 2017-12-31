package com.cegeka.xparduino.state.component.monitor;

import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.ArduinoTestRule;
import com.cegeka.xparduino.event.impl.temperaturesensor.TemperatureSensorEvent;
import com.cegeka.xparduino.state.component.impl.TemperatureSensorState;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.function.Consumer;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.component.ComponentTestConstants.temperatureSensor;
import static com.cegeka.xparduino.state.component.monitor.ComponentStateMonitor.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class ComponentStateMonitorTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Rule
    public ArduinoTestRule arduinoTestRule = new ArduinoTestRule(temperatureSensor(DIGITAL_0));

    @Mock
    private Consumer<TemperatureSensorState> actionConsumer;
    @Captor
    private ArgumentCaptor<TemperatureSensorState> temperatureSensorStateCaptor;

    private Arduino arduino;

    @Before
    public void setUp() throws Exception {
        arduino = arduinoTestRule.arduino();
    }

    @Test
    public void whenConditionIsMet_thenTheActionWillBeExecuted() throws Exception {
        TemperatureSensorState temperatureSensorState =
                arduino.getState(DIGITAL_0, TemperatureSensorState.class);

        given(temperatureSensorState)
                .when(state -> state.getDegrees() == 20)
                .then(actionConsumer);

        arduinoTestRule.emitEvents(new TemperatureSensorEvent(DIGITAL_0, 20));

        verify(actionConsumer).accept(temperatureSensorStateCaptor.capture());
        assertThat(temperatureSensorStateCaptor.getValue().getDegrees()).isEqualTo(20);
    }

    @Test
    public void whenConditionIsNotMet_thenTheActionWillNotBeExecuted() throws Exception {
        TemperatureSensorState temperatureSensorState =
                arduino.getState(DIGITAL_0, TemperatureSensorState.class);

        given(temperatureSensorState)
                .when(state -> state.getDegrees() == 21)
                .then(actionConsumer);

        arduinoTestRule.emitEvents(new TemperatureSensorEvent(DIGITAL_0, 20));

        verifyZeroInteractions(actionConsumer);
    }
}