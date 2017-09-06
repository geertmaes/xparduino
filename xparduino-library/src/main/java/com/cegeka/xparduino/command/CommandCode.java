package com.cegeka.xparduino.command;

import java.util.Arrays;

public enum CommandCode {

    BASE_LED_COMMAND(0),
    TRACK_SWITCH_COMMAND(1),
    TRAIN_COMMAND(1);

    private final int value;

    CommandCode(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static CommandCode valueOf(int value) {
        return Arrays.stream(values())
                .filter(type -> type.value() == value)
                .findAny()
                .orElse(null);
    }
}
