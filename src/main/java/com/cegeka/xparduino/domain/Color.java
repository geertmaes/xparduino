package com.cegeka.xparduino.domain;

import java.util.Arrays;

public enum Color {

    RED(0), BLUE(1);

    private final int code;

    Color(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Color valueOf(int code) {
        return Arrays.stream(values())
                .filter(type -> type.getCode() == code)
                .findAny()
                .orElse(null);
    }
}