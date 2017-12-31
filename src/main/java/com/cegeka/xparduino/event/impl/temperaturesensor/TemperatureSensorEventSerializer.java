package com.cegeka.xparduino.event.impl.temperaturesensor;

import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.event.EventDataSerializer;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

import static com.cegeka.xparduino.component.ComponentType.TEMPERATURE_SENSOR;
import static com.cegeka.xparduino.event.EventCode.TEMPERATURE_SENSOR_EVENT;
import static java.lang.String.valueOf;

public class TemperatureSensorEventSerializer implements EventDataSerializer<TemperatureSensorEvent> {

    @Override
    public SerializedEvent serialize(TemperatureSensorEvent event) {
        String degrees = valueOf(event.getDegrees());
        return new SerializedEvent(TEMPERATURE_SENSOR_EVENT, degrees, new Component(event.getPin(), TEMPERATURE_SENSOR));
    }
}