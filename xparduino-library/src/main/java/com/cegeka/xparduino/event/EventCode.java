package com.cegeka.xparduino.event;

import java.util.Arrays;

public enum EventCode {

    BASE_LED_EVENT(0),
    PHOTO_SENSOR_EVENT(1),
    INFRARED_EMITTER_EVENT(2),
    OBSTACLE_SENSOR_EVENT(3),
    TRACK_SWITCH_EVENT(4),
    RFID_READER_EVENT(5);

    private final int value;

    EventCode(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static EventCode valueOf(int value) {
        return Arrays.stream(values())
                .filter(type -> type.value() == value)
                .findAny()
                .orElse(null);
    }
}
