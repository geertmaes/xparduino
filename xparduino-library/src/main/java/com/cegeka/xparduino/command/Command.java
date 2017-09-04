package com.cegeka.xparduino.command;

import com.cegeka.xparduino.component.Component;

public interface Command {

    String getAction();

    Component getComponent();

}
