package com.cegeka.xpdays.arduino.communication;


import com.cegeka.xpdays.arduino.command.Command;

import java.io.Closeable;

public interface CommandChannel extends Closeable{

    void send(Command command);
}
