package com.cegeka.xparduino.queue;

import com.cegeka.xparduino.channel.ChannelListener;
import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.mapper.CommandMapper;
import com.cegeka.xparduino.command.serialized.SerializedCommand;

public class ArduinoQueueCommandSender implements ChannelListener<Command> {

    private final ArduinoQueueSender queueSender;
    private final CommandMapper commandMapper;

    public ArduinoQueueCommandSender(ArduinoQueueSender queueSender, CommandMapper commandMapper) {
        this.queueSender = queueSender;
        this.commandMapper = commandMapper;
    }

    @Override
    public void on(Command command) {
        SerializedCommand serializedCommand = commandMapper.toSerializedCommand(command);
        queueSender.send(serializedCommand.toString());
    }
}
