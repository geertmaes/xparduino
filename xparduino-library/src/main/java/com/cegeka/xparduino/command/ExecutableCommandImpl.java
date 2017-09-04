package com.cegeka.xparduino.command;

import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.component.Component;

public class ExecutableCommandImpl<T extends Command> implements ExecutableCommand<T> {

    public static <T extends Command> ExecutableCommand<T> executable(T delegate, Channel<Command> commandChannel) {
        return new ExecutableCommandImpl<>(delegate, commandChannel);
    }

    private final T delegate;
    private final Channel<Command> commandChannel;

    ExecutableCommandImpl(T delegate, Channel<Command> commandChannel) {
        this.delegate = delegate;
        this.commandChannel = commandChannel;
    }

    @Override
    public void execute() {
        this.commandChannel.send(this);
    }

    @Override
    public String getAction() {
        return delegate.getAction();
    }

    @Override
    public Component getComponent() {
        return delegate.getComponent();
    }

}
