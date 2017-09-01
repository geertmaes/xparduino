package com.cegeka.xparduino.queue;

import com.cegeka.xparduino.channel.ChannelListener;
import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.serialization.CommandSerializer;

public class ArduinoQueueCommandSender implements ChannelListener<Command> {

    private final ArduinoQueueSender queueSender;
    private final CommandSerializer commandSerializer;

    public ArduinoQueueCommandSender(ArduinoQueueSender queueSender) {
        this.queueSender = queueSender;
        this.commandSerializer = new CommandSerializer();
    }

    @Override
    public void on(Command command) {
        queueSender.send(commandSerializer.serialize(command));
    }
}
