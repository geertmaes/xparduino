package com.cegeka.xpdays.arduino.communication;


import com.cegeka.xpdays.arduino.command.Command;

public interface CommandChannel {

    void send(Command command);
}
