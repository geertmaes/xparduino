package com.cegeka.xpdays.arduino.common;

public enum Direction {

    LEFT, RIGHT;

    public Direction toggle(){
        return this == LEFT ? RIGHT : LEFT;
    }
}
