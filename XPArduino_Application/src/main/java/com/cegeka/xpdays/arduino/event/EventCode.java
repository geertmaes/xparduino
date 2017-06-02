package com.cegeka.xpdays.arduino.event;

import java.util.Arrays;

public enum EventCode {

    BASE_LED_EVENT(0),
    PHOTO_SENSOR_EVENT(1),
    INFRARED_EMITTER_EVENT(2),
    OBSTACLE_SENSOR_EVENT(3),
    SWITCH_EVENT(4),
    RFID_EVENT(5);

    private final int value;

    EventCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EventCode valueOf(int value) {
        return Arrays.stream(values())
                .filter(type -> type.getValue() == value)
                .findAny()
                .orElse(null);
    }
}
