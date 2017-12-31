package com.cegeka.xparduino.domain;

public enum Direction {

    LEFT, RIGHT;

    public Direction toggle() {
        return this == LEFT ? RIGHT : LEFT;
    }
}