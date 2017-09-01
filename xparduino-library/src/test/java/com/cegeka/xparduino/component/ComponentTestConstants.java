package com.cegeka.xparduino.component;

import static com.cegeka.xparduino.component.ComponentType.BASE_LED;
import static com.cegeka.xparduino.component.ComponentType.TRACK_SWITCH;

public class ComponentTestConstants {

    public static int PIN_1 = 1;
    public static int PIN_2 = 2;

    public static Component baseLed(int pin) {
        return new Component(pin, BASE_LED);
    }

    public static Component trackSwitch(int pin) {
        return new Component(pin, TRACK_SWITCH);
    }

}
