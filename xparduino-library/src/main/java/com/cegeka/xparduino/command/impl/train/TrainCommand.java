package com.cegeka.xparduino.command.impl.train;

import com.cegeka.xparduino.command.AbstractCommand;
import com.cegeka.xparduino.command.CommandMapping;
import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.domain.Color;

import static com.cegeka.xparduino.command.CommandCode.TRAIN_COMMAND;
import static java.lang.String.format;

@CommandMapping(
        code = TRAIN_COMMAND,
        serializer = TrainCommandSerializer.class,
        deserializer = TrainCommandDeserializer.class)
public class TrainCommand extends AbstractCommand {

    private final int speed;
    private final int channel;
    private final Color color;

    public TrainCommand(ComponentPin pin, int speed, int channel, Color color) {
        super(pin);
        this.speed = speed;
        this.channel = channel;
        this.color = color;
    }

    @Override
    public String getAction() {
        return format("%d:%d:%d", color.getCode(), channel, speed);
    }

    @Override
    protected ComponentType getComponentType() {
        return ComponentType.INFRARED_EMITTER;
    }

}
