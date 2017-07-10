package com.cegeka.xparduino.state.component.impl;

import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.event.impl.photosensor.PhotoSensorEvent;
import com.cegeka.xparduino.state.component.ComponentState;

public class PhotoSensorState extends ComponentState<PhotoSensorState> {

    private int signal;

    public PhotoSensorState(int pin) {
        super(pin);
    }

    public void on(PhotoSensorEvent event) {
        signal = event.getSignal();
    }

    public int isSignal() {
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

        PhotoSensorState that = (PhotoSensorState) o;

        return signal == that.signal;
    }

    @Override
    public int hashCode() {
        return signal;
    }
}
