package com.cegeka.xparduino.command.impl.baseled;

import com.cegeka.xparduino.event.impl.baseled.BaseLedEvent;
import org.junit.Test;

import static com.cegeka.xparduino.command.CommandTestConstants.baseLedCommand;
import static com.cegeka.xparduino.event.EventTestConstants.baseLedEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class BaseLedCommandBehaviourTest {

    private final BaseLedCommandBehaviour behaviour = new BaseLedCommandBehaviour();

    @Test
    public void create_Emitting_True() throws Exception {
        BaseLedEvent event = behaviour.create(baseLedCommand(true));

        assertThat(event).isEqualTo(baseLedEvent(true));
    }

    @Test
    public void create_Emitting_False() throws Exception {
        BaseLedEvent event = behaviour.create(baseLedCommand(false));

        assertThat(event).isEqualTo(baseLedEvent(false));
    }

}