package com.cegeka.xparduino.component;

import java.util.Arrays;

public enum ComponentPin {

    DIGITAL_0(0),
    DIGITAL_1(1),
    DIGITAL_2(2),
    DIGITAL_3(3),
    DIGITAL_4(4),
    DIGITAL_5(5),
    DIGITAL_6(6),
    DIGITAL_7(7),
    DIGITAL_8(8),
    DIGITAL_9(9),
    DIGITAL_10(10),
    DIGITAL_11(11),
    DIGITAL_12(12),
    DIGITAL_13(13),
    ANALOG_0(14),
    ANALOG_1(15),
    ANALOG_2(16),
    ANALOG_3(17),
    ANALOG_4(18),
    ANALOG_5(19);

    private final int value;

    ComponentPin(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static ComponentPin valueOf(int value) {
        return Arrays.stream(values())
                .filter(type -> type.value() == value)
                .findAny()
                .orElse(null);
    }

}
