package com.cegeka.xpdays.arduino.command;

import com.cegeka.xpdays.arduino.component.Component;

public interface Command {

    String getAction();

    Component getComponent();
}
