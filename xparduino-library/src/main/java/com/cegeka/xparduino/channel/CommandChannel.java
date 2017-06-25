package com.cegeka.xparduino.channel;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.CommandSerializer;
import com.cegeka.xparduino.queue.ArduinoQueue;

import java.io.IOException;

public class CommandChannel implements Channel<Command> {

    private final ArduinoQueue queue;
    private final CommandSerializer commandSerializer;

    public CommandChannel(ArduinoQueue queue) {
        this.queue = queue;
        this.commandSerializer = new CommandSerializer();
    }

    @Override
    public void send(Command command) {
        String message = commandSerializer.serialize(command);
        queue.send(message);
    }

    @Override
    public void close() throws IOException {
        queue.close();
    }
}
