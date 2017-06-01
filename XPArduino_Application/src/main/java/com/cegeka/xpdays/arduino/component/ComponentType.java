package com.cegeka.xpdays.arduino.component;

import java.util.Arrays;

public enum ComponentType {

    BASE_LED(0),
    PHOTO_SENSOR(1);

    private final int value;

    ComponentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ComponentType valueOf(int value) {
        return Arrays.stream(values())
                .filter(type -> type.getValue() == value)
                .findAny()
                .orElse(null);
    }
}
