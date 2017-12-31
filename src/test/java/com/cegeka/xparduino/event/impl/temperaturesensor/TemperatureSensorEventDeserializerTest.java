package com.cegeka.xparduino.event.impl.temperaturesensor;

import com.cegeka.xparduino.event.impl.temperaturesensor.TemperatureSensorEvent;
import com.cegeka.xparduino.event.impl.temperaturesensor.TemperatureSensorEventDeserializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;
import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentPin.ANALOG_1;
import static com.cegeka.xparduino.component.ComponentTestConstants.temperatureSensor;
import static com.cegeka.xparduino.event.EventCode.TEMPERATURE_SENSOR_EVENT;
import static org.assertj.core.api.Assertions.assertThat;

public class TemperatureSensorEventDeserializerTest {

    private static final double DEGREES_VALUE = 21.01;
    private static final String DEGREES = String.valueOf(DEGREES_VALUE);

    private final TemperatureSensorEventDeserializer deserializer = new TemperatureSensorEventDeserializer();

    @Test
    public void deserialize() throws Exception {
        SerializedEvent serializedEvent =
                new SerializedEvent(TEMPERATURE_SENSOR_EVENT, DEGREES, temperatureSensor(ANALOG_1));

        TemperatureSensorEvent sensorEvent = deserializer.deserialize(serializedEvent);

        assertThat(sensorEvent)
                .isEqualTo(new TemperatureSensorEvent(ANALOG_1, DEGREES_VALUE));
    }
}