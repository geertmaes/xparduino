package com.cegeka.xparduino.component;

import static com.cegeka.xparduino.component.ComponentType.*;

public class ComponentTestConstants {

    public static int PIN_1 = 1;
    public static int PIN_2 = 2;

    public static Component baseLed(int pin) {
        return new Component(pin, BASE_LED);
    }

    public static Component photoSensor(int pin) {
        return new Component(pin, PHOTO_SENSOR);
    }

    public static Component infrared(int pin) {
        return new Component(pin, INFRARED_EMITTER);
    }

    public static Component obstacleSensor(int pin) {
        return new Component(pin, OBSTACLE_SENSOR);
    }

    public static Component rfidReader(int pin) {
        return new Component(pin, RFID_READER);
    }

    public static Component trackSwitch(int pin) {
        return new Component(pin, TRACK_SWITCH);
    }

}
