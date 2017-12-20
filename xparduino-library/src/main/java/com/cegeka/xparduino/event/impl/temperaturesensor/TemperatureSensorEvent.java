package com.cegeka.xparduino.event.impl.temperaturesensor;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.event.AbstractEvent;
import com.cegeka.xparduino.event.EventMapping;

import static com.cegeka.xparduino.event.EventCode.BASE_LED_EVENT;
import static com.cegeka.xparduino.event.EventCode.TEMPERATURE_SENSOR_EVENT;

@EventMapping(
        code = TEMPERATURE_SENSOR_EVENT,
        serializer = TemperatureSensorEventSerializer.class,
        deserializer = TemperatureSensorEventDeserializer.class)
public class TemperatureSensorEvent extends AbstractEvent {

    private final double degrees;

    public TemperatureSensorEvent(ComponentPin pin, double degrees) {
        super(pin);
        this.degrees = degrees;
    }

    public double getDegrees() {
        return degrees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TemperatureSensorEvent that = (TemperatureSensorEvent) o;

        return degrees == that.degrees;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(degrees);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
