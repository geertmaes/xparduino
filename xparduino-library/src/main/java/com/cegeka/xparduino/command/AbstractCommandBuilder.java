package com.cegeka.xparduino.command;

import com.cegeka.xparduino.channel.Channel;

import java.util.concurrent.ScheduledExecutorService;

import static com.cegeka.xparduino.command.ExecutableCommandImpl.executable;
import static com.cegeka.xparduino.command.RepeatingCommandImpl.repeating;

public abstract class AbstractCommandBuilder<T extends Command> {

    private final Channel<Command> commandChannel;
    private final ScheduledExecutorService executorService;

    protected final int pin;

    protected AbstractCommandBuilder(int pin,
                                     Channel<Command> commandChannel,
                                     ScheduledExecutorService executorService) {
        this.pin = pin;
        this.commandChannel = commandChannel;
        this.executorService = executorService;
    }

    public abstract T build();

    public ExecutableCommand<T> execute() {
        return executable(build(), commandChannel);
    }

    public RepeatingCommand<T> repeat() {
        return repeating(build(), commandChannel, executorService);
    }

}
