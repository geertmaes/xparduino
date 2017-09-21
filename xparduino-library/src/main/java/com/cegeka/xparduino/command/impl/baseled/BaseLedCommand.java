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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BaseLedCommand that = (BaseLedCommand) o;

        return emitting == that.emitting;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (emitting ? 1 : 0);
        return result;
    }

}
