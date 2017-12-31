package com.cegeka.xparduino.command;

public interface ExecutableCommand<T extends Command> extends Command {

    void execute();
}
