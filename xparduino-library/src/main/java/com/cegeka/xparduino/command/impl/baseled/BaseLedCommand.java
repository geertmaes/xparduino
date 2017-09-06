package com.cegeka.xparduino.command.impl.baseled;

import com.cegeka.xparduino.command.AbstractCommand;
import com.cegeka.xparduino.command.CommandMapping;
import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.component.ComponentType;

import static com.cegeka.xparduino.command.CommandCode.BASE_LED_COMMAND;
import static com.cegeka.xparduino.component.ComponentType.BASE_LED;

@CommandMapping(
        code = BASE_LED_COMMAND,
        serializer = BaseLedCommandSerializer.class,
        deserializer = BaseLedCommandDeserializer.class)
public class BaseLedCommand extends AbstractCommand {

    private final boolean emitting;

    public BaseLedCommand(ComponentPin pin, boolean emitting) {
        super(pin);
        this.emitting = emitting;
    }

    public boolean isEmitting() {
        return emitting;
    }

    @Override
    public String getAction() {
        return emitting ? "ON" : "OFF";
    }

    @Override
    protected ComponentType getComponentType() {
        return BASE_LED;
    }

}
