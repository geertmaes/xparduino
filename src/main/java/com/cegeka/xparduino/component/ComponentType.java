package com.cegeka.xparduino.component;

import java.util.Arrays;

public enum ComponentType {

    BASE_LED(0),
    PHOTO_SENSOR(1),
    INFRARED_EMITTER(2),
    OBSTACLE_SENSOR(3),
    TRACK_SWITCH(4),
    RFID_READER(5),
    TEMPERATURE_SENSOR(5);

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
