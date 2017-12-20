package com.cegeka.xparduino.component;

import static com.cegeka.xparduino.component.ComponentType.*;

public class ComponentTestConstants {

    public static Component baseLed(ComponentPin pin) {
        return new Component(pin, BASE_LED);
    }

    public static Component photoSensor(ComponentPin pin) {
        return new Component(pin, PHOTO_SENSOR);
    }

    public static Component infrared(ComponentPin pin) {
        return new Component(pin, INFRARED_EMITTER);
    }

    public static Component obstacleSensor(ComponentPin pin) {
        return new Component(pin, OBSTACLE_SENSOR);
    }

    public static Component rfidReader(ComponentPin pin) {
        return new Component(pin, RFID_READER);
    }

    public static Component trackSwitch(ComponentPin pin) {
        return new Component(pin, TRACK_SWITCH);
    }

    public static Component temperatureSensor(ComponentPin pin) {
        return new Component(pin, TEMPERATURE_SENSOR);
    }

}
