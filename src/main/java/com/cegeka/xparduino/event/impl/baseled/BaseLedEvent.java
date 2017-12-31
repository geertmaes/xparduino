package com.cegeka.xparduino.event.impl.baseled;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.event.AbstractEvent;
import com.cegeka.xparduino.event.EventMapping;

import static com.cegeka.xparduino.event.EventCode.BASE_LED_EVENT;

@EventMapping(
        code = BASE_LED_EVENT,
        serializer = BaseLedEventSerializer.class,
        deserializer = BaseLedEventDeserializer.class)
public class BaseLedEvent extends AbstractEvent {

    private final boolean emitting;

    public BaseLedEvent(ComponentPin pin, boolean emitting) {
        super(pin);
        this.emitting = emitting;
    }

    public boolean isEmitting() {
        return emitting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BaseLedEvent that = (BaseLedEvent) o;

        return emitting == that.emitting;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (emitting ? 1 : 0);
        return result;
    }

}
