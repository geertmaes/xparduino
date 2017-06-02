package com.cegeka.xpdays.arduino.state.photosensor;

import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.state.ComponentState;

public class PhotoSensorState extends ComponentState {

    private int signal;

    public PhotoSensorState(int pin) {
        super(pin);
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    public int isSignal() {
        return signal;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.PHOTO_SENSOR;
    }
}
