package com.cegeka.xparduino.state.component.impl;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.event.impl.temperaturesensor.TemperatureSensorEvent;
import com.cegeka.xparduino.state.component.ComponentState;

import java.util.function.Consumer;

import static com.cegeka.xparduino.component.ComponentType.TEMPERATURE_SENSOR;

public class TemperatureSensorState extends ComponentState<TemperatureSensorState> {

    private double degrees;

    public TemperatureSensorState(ComponentPin pin) {
        super(pin);
    }

    public void on(TemperatureSensorEvent event) {
        degrees = event.getDegrees();
    }

    public double getDegrees() {
        return degrees;
    }

    public void onTemperatureChange(Consumer<Double> consumer) {
        onStateChange((prev, curr) -> consumer.accept(curr.degrees));
    }

    @Override
    public ComponentType getComponentType() {
        return TEMPERATURE_SENSOR;
    }

    @Override
    public TemperatureSensorState copy() {
        TemperatureSensorState state = new TemperatureSensorState(getPin());
        state.degrees = degrees;
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TemperatureSensorState that = (TemperatureSensorState) o;

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
