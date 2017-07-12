package com.cegeka.xparduino.componentaction.handler.impl;

import com.cegeka.xparduino.componentaction.Action;
import com.cegeka.xparduino.componentaction.handler.AbstractComponentHandler;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.impl.baseled.BaseLedEvent;

import java.util.stream.Stream;

import static com.cegeka.xparduino.component.ComponentType.BASE_LED;

public class BaseLedComponentHandler extends AbstractComponentHandler {

    public BaseLedComponentHandler() {
        super(BASE_LED);
    }

    @Override
    protected Stream<Event> handle(int pin, Action action) {
        boolean emitting = action.value()
                .equalsIgnoreCase("on");

        return Stream.of(new BaseLedEvent(pin, emitting));
    }

}
