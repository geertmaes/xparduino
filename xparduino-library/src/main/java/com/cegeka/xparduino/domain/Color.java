package com.cegeka.xparduino.domain;

public enum Color {

    RED(0), BLUE(1);

    private final int color;

    Color(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}