package com.cegeka.xparduino.state.component.impl;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.event.impl.photosensor.PhotoSensorEvent;
import com.cegeka.xparduino.state.component.ComponentState;

public class PhotoSensorState extends ComponentState<PhotoSensorState> {

    private int signal;

    public PhotoSensorState(ComponentPin pin) {
        super(pin);
    }

    public void on(PhotoSensorEvent event) {
        signal = event.getSignal();
    }

    public int getSignal() {
        return signal;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.PHOTO_SENSOR;
    }

    @Override
    public PhotoSensorState copy() {
        PhotoSensorState state = new PhotoSensorState(getPin());
        state.signal = signal;
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PhotoSensorState that = (PhotoSensorState) o;

        return signal == that.signal;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + signal;
        return result;
    }
}
