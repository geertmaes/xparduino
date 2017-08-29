package com.cegeka.xparduino.component;

import static com.cegeka.xparduino.component.ComponentType.BASE_LED;

public class ComponentTestConstants {

    public static int PIN_1 = 1;
    public static int PIN_2 = 2;

    public static Component baseLed(int pin) {
        return new Component(pin, BASE_LED);
    }

}
