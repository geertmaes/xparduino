package com.cegeka.xparduino.command.impl;

import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.command.AbstractCommandBuilder;
import com.cegeka.xparduino.command.Command;

import java.util.concurrent.ScheduledExecutorService;

public class BaseLedCommandBuilder extends AbstractCommandBuilder<BaseLedCommand> {

    private boolean emitting = false;

    public BaseLedCommandBuilder(int pin,
                                 Channel<Command> commandChannel,
                                 ScheduledExecutorService executorService) {
        super(pin, commandChannel, executorService);
    }

    public BaseLedCommandBuilder on() {
        this.emitting = true;
        return this;
    }

    public BaseLedCommandBuilder off() {
        this.emitting = false;
        return this;
    }

    public BaseLedCommandBuilder withEmitting(boolean emitting) {
        this.emitting = emitting;
        return this;
    }

    @Override
    public BaseLedCommand build() {
        return new BaseLedCommand(pin, emitting);
    }

}
