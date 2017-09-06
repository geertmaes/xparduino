package com.cegeka.xparduino.command.impl.train;

import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.command.AbstractCommandBuilder;
import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.domain.Color;

import java.util.concurrent.ScheduledExecutorService;

public class TrainCommandBuilder extends AbstractCommandBuilder<TrainCommand> {

    private int speed;
    private int channel;
    private Color color;

    public TrainCommandBuilder(int pin,
                               Channel<Command> commandChannel,
                               ScheduledExecutorService executorService) {
        super(pin, commandChannel, executorService);
    }

    public TrainCommandBuilder withSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public TrainCommandBuilder withColor(Color color) {
        this.color = color;
        return this;
    }

    public TrainCommandBuilder withChannel(int channel) {
        this.channel = channel;
        return this;
    }

    @Override
    public TrainCommand build() {
        return new TrainCommand(pin, speed, channel, color);
    }

}
