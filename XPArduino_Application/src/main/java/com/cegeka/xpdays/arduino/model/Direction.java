package com.cegeka.xpdays.arduino.model;

public enum Direction {

    LEFT, RIGHT;

    public Direction toggle(){
        return this == LEFT ? RIGHT : LEFT;
    }
}
