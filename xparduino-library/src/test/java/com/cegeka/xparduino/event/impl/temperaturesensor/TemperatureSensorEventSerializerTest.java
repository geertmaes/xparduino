package com.cegeka.xparduino.event.impl.temperaturesensor;

import com.cegeka.xparduino.event.impl.temperaturesensor.TemperatureSensorEvent;
import com.cegeka.xparduino.event.impl.temperaturesensor.TemperatureSensorEventSerializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;
import org.junit.Test;

import static com.cegeka.xparduino.component.ComponentPin.ANALOG_1;
import static com.cegeka.xparduino.component.ComponentTestConstants.temperatureSensor;
import static com.cegeka.xparduino.event.EventCode.TEMPERATURE_SENSOR_EVENT;
import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class TemperatureSensorEventSerializerTest {

    private static final double DEGREES = 21.01;

    private final TemperatureSensorEventSerializer serializer = new TemperatureSensorEventSerializer();

    @Test
    public void serialize() throws Exception {
        SerializedEvent serializedEvent =
                serializer.serialize(new TemperatureSensorEvent(ANALOG_1, DEGREES));

        assertThat(serializedEvent)
                .isEqualTo(new SerializedEvent(TEMPERATURE_SENSOR_EVENT, valueOf(DEGREES), temperatureSensor(ANALOG_1)));
    }
}