package com.cegeka.xparduino.command;

import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.component.ComponentPin;

import static com.cegeka.xparduino.command.ExecutableCommandImpl.executable;

public abstract class AbstractCommandBuilder<T extends Command> {

    private final ComponentPin pin;
    private final Channel<Command> commandChannel;

    protected AbstractCommandBuilder(ComponentPin pin,
                                     Channel<Command> commandChannel) {
        this.pin = pin;
        this.commandChannel = commandChannel;
    }

    protected ComponentPin pin() {
        return pin;
    }

    public abstract T build();

    public void execute() {
        executable(build(), commandChannel).execute();
    }

    public RepeatingCommand<T> repeat() {
        return RepeatingCommandImpl.repeating(build(), commandChannel);
    }

}
