package com.cegeka.xparduino.command;

import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.component.ComponentPin;

import java.util.concurrent.ScheduledExecutorService;

import static com.cegeka.xparduino.command.ExecutableCommandImpl.executable;

public abstract class AbstractCommandBuilder<T extends Command> {

    private final ComponentPin pin;
    private final Channel<Command> commandChannel;
    private final ScheduledExecutorService executorService;

    protected AbstractCommandBuilder(ComponentPin pin,
                                     Channel<Command> commandChannel,
                                     ScheduledExecutorService executorService) {
        this.pin = pin;
        this.commandChannel = commandChannel;
        this.executorService = executorService;
    }

    protected ComponentPin pin() {
        return pin;
    }

    public abstract T build();

    public ExecutableCommand<T> executing() {
        return executable(build(), commandChannel);
    }

    public RepeatingCommand<T> repeating() {
        return RepeatingCommandImpl.repeating(build(), commandChannel, executorService);
    }

}
